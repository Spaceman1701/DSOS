# Domain Specific Operating System

## About

This is a repo for my undergraduate thesis project. I'll be writing a programming language and an OS. The goal of the project is to demonstrate
novel ways in which OS level hardware access can support a high level programming language runtime. This research should be seen as a variant
of Library OS research. However, instead of taking an existing low-level language I will be writing a tailored high-level language. In addition, most
library OS research takes the Linux userspace ABI and conditionally includes features as required. I believe this limits to potential to library OS
as the Linux ABI is generic and low level.

## Misc

The plan for this project is still a work in progress. Anything here could change at any moment. Most likely my goals will be scaled back
as I inevitably run out of time. 

# Details

## OS Features

- OS as language runtime. No "userspace" and "kernel" devision.
- High-level ABI - bytecode interpreter as ABI
- Garbage Collection
- Cooperative Scheduling
- High-level networking support

## Language Features

- Garbage collected
- Portable
- First-class co-routines

## Motivation Sorta

Most Library OS projects focus on systems programming languages like C++. This is interesting, but most projects where a Library OS make
sense are written in very high-level languages like Python or Java. I'd like to see if a performance improvment over Linux can be achieved
for a very high-level language. 

## Performance

Performance is difficult to measure and has many dimensions. I think the most interesting measures of performance for this project will be:
* Start-up time | This is important for serverless use-cases. Linux kernels can take an enmormous amount of time to boot up (relatively speaking). In addition, the actual
user code has to be sent to the machine after the kernal has started
* Latency | In web use cases latency matters a lot for the end user. The amount of data being sent and recieved per-client isn't very high, but clients need to be served as fast as possible
* IPC | A higher IPC means lower CPU usage to do the same task. For web use cases, CPU performance is often extermely limited (for example, the lowest tier AWS server has only 1 CPU)
* Memory Usage | Linux can use nearly 1 GB of RAM in some configurations. For low-cost web servers, the system often has less than 1 GB total.
