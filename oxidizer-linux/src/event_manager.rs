use std::sync::mpsc;
use std::sync::mpsc::{Sender, Receiver};
use crate::http;
use crate::object::{ObjRef, Object};
use std::process::exit;
use std::collections::HashMap;

pub enum Event {
    AllThreadsFinished,
    HttpEvent(http::HttpRequest),
}

pub enum EventListener {
    Http(HttpEventListener),
    AllThreadsDone(AllThreadsDoneEventListener),
}

pub struct HttpEventListener {
    method: String,
    path: String,
    thread_id: usize,
}

pub struct AllThreadsDoneEventListener {
    thread_id: usize,
}

pub struct EventManager {
    pub sender: Sender<Event>,
    pub receiver: Receiver<Event>,

    event_buffer: Vec<Event>,

    event_listeners: HashMap<usize, EventListener>,
}


impl EventManager {


    pub fn new() -> EventManager {
        let (sender, receiver) = mpsc::channel();

        EventManager {
            sender,
            receiver,

            event_listeners: HashMap::new(),
            event_buffer: vec![]
        }
    }

    pub fn register_listener(&mut self, template: &Object, thread_id: usize) {
        if template.fields.contains_key("method") && template.fields.contains_key("path") {
            let method_value = match template.fields.get("method").unwrap() {
                ObjRef::String(_, value) => String::from(*value),
                _ => {
                    println!("bad");
                    exit(-1);
                }
            };
            let path_value = match template.fields.get("path").unwrap() {
                ObjRef::String(_, value) => String::from(*value),
                _ => {
                    println!("bad");
                    exit(-1);
                }
            };


            self.event_listeners.insert(thread_id, EventListener::Http(HttpEventListener {
                method: method_value,
                path: path_value,
                thread_id
            }));
        }
    }

    pub fn load_events(&mut self) {
        for event in self.receiver.iter() { //copy all events into buffer
            self.event_buffer.push(event);
        }
    }

    pub fn find_event_for_thread(&mut self, thread_id: usize) -> Option<Event> {
        let mut found = None;
        for (index, event) in self.event_buffer.iter().enumerate() {
            if let Some(event_listener) = self.event_listeners.get(&thread_id) {
                found = Some(index)
            }
        }

        found.map(|index| self.event_buffer.remove(index))
    }

    pub fn get_new_sender(&self) -> Sender<Event> {
        self.sender.clone()
    }

}

impl Event {
    pub fn matches_listener(&self, listener: &EventListener) -> bool {
        match (self, listener) {
            (Event::HttpEvent(request), EventListener::Http(template)) => {
                request.path == template.path && request.method == template.method
            }

            (Event::AllThreadsFinished, EventListener::AllThreadsDone(_)) => true,

            _ => false
        }
    }
}