use std::sync::mpsc;
use std::sync::mpsc::{Sender, Receiver};
use crate::http;
use crate::object::{ObjRef, Object};

pub struct  Event {
    pub target: usize,
    pub data: EventData
}

pub enum EventData {
    AllThreadsFinished,
    HttpEvent(http::HttpRequest),
}

pub struct EventEndpoint {

}

pub struct EventManager {
    pub sender: Sender<Event>,
    pub receiver: Receiver<Event>,

    event_buffer: Vec<Event>
}


impl EventManager {


    pub fn new() -> EventManager {
        let (sender, receiver) = mpsc::channel();

        EventManager {
            sender,
            receiver,

            event_buffer: vec![]
        }
    }

    pub fn register_listener(&mut self, template: &Object, thread_id: usize) {

    }

    pub fn load_events(&mut self) {
        for event in self.event_manager.receiver.try_iter() { //copy all events into buffer
            self.event_buffer.push(event);
        }

        //sort events by registration template
    }

    pub fn find_event_for_thread(&mut self, thread_id: usize) -> Option<Event> {
        let mut found = None;
        for (index, event) in self.event_buffer.iter().enumerate() {
            if event.target == thread_id {
                found = Some(index);
            }
        }

        found.map(|index| self.event_buffer.remove(index))
    }

}

impl Event {
    pub fn store_into_ox_obj(self, target: &mut Object) {

    }
}