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
- No file system. Web applications don't need persistant storage. Maayybbee temportary serialization to the disk

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
* Memory Usage | Linux can use nearly 1 GB of RAM in some configurations. For low-cost web servers, the system often has less than 1 GB total (e.g. AWS t3.micro, the cheapest x86_64 instance has 2 CPUs and 512mb).

## Similar Projects

This project is a unikernal. There are lots of unikernals: [http://unikernel.org/projects/](http://unikernel.org/projects/)

The biggest difference between current projects and this one is in its goal: This project is hyper-focused on using low-level OS
features to directly support a high-level language. The high-level language will not be able to interact with the hardware directly.
The PL for this project can NOT be used to build a proper operating system the way many unikernals are used. This OS/Language is for
application developers.

However, this project differentiates itself in a couple ways:

1) The OS is a byte-code VM
2) Targeted specifically to improve performance on low-cost virtualized hardware
3) Focus specifically on the domain of micro-web services / _extremely_ high level OS interfaces
4) No file system

To break this down further, I'll go over the different catagories of cloud-targeting unikernels

### Language runtime on micro-kernal

These projects provide a simple single-application kernel and than target a compiler at it. 
A prominate example is OSv. OSv is a custom kernel with language stacks for a few high level languages.
While there are plans to allow language runtimes closer access to hardware features in OSv, they have yet
to materialize.

This project is different from these attempts as the kernel and byte-code language are designed together. The
hypothesis is that this will result in better performance and expressiveness.

### Linux ABI as a Library

These projects are designed to run applications that are allready written. They provide a staticly-linkable 
Linux ABI that allows most existing code to be compiled against it.

This project is different as it is a ground-up approach. There is no Linux ABI at all. In fact, there is no ABI 
in the traditional sense.

### Very High Level Language Unikernals

These are the most similar to this project. Good examples are HaLVM for Haskell, runtime.js for JavaScript, and MirageOS for OCaml. These 
projects attempt to build a kernel that targets a single very high level language.

This project differs in scope and design. First, the OS is a byte-code machine. All examples of this type of unikernal that 
I've been able to find fall into one of the two previous catagories as well. Runtime.js uses the V8 engine compiled against a
custom micro-kernel. HaLVM is implemented as a target for the GHC. These pojects tend to focus on the OS research implications of 
the project as well. HalVM is designed as a testbed for OS components. Haskell was chosen to make prototyping faster. Similarly, MirageOS
is a project to build flexible OS libraries in OCaml. In fact, the most interesting parts of this project probably could be implemented using
HalVM or MirageOS. 

(runtime.js is seemingly abandoned before major features were complete. For example, it doesn't support CPU threads)

### Domain Specific Unikernals

I've only found one of these, and it's Clive. Clive is a unikernal targeted at the domain of distributed cloud systems. It supports a 
very high level language - Go. However, Clive also falls into the first catagory as a modified version of the Go compiler is targeted 
at a semi-custom kernel. However, while Clive is planned to move to a custom kernel, it's current BSD based.  

This project also differs from Clive in domain. While Clive is designed for the development distributed CSP systems, this project targets
low cost single machine web applications. Furthermore, Clive has a strong focus on its distributed file system. This project will have
no filesystem. This is a product of the different domains. Furthermore, Go is a compiled language that, while very popular, does not nearly
have the adoption of interpreted languages like Python or Ruby. 

## Architecture

There are four high-level components of the design:

* compiler - reads source files and produces bytecode binaries. Since the compiler doesn't need to run on bare metal,
it can be written in any language and can depend on a standard library. At some point, I might decide to seperate the 
assembler and compiler into seperate components.

* runtime - this is the shared language runtime. This code must compile in freestanding mode to run on Linux or on bare metal. This will
include the bytecode interpreter and the implementation of most bytecode instructions. 

* linux-backend - this is the runtime backend for Linux. It will include the Linux GC implementation, Linux thread scheduler, and Linux IO
implementations.

* metal-backend - this is the bare metal runtime backend. It will be a unikernal supporting a GC, cooperative scheduler, and native IO.

The goal of this architecture is to allow experiments that demonstrate the performance charactaristics of the different interpreter backends. Thus,
the goal is to minimize code duplication. However, since the bare metal implementation provides more fine-grained hardware access, some duplication will
probably be necessary