def main() {
    spawn fooBarHandler;


    listen new AllThreadsDoneEventTemplate(); //vm will send "all threads done" event when all coroutines exit
}


def fooBarHandler() {
    while true {
         httpRequest = listen new HttpEventTemplate("GET", "/foo/bar");

         println(httpRequest.body);

         send new EmptyHttpResponse(200);
    }
}