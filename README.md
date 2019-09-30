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
