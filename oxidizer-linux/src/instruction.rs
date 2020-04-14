use std::mem;
use crate::instruction::Instruction::*;
pub enum Instruction {
    Store(u16),
    LoadConstInt(i64),
    LoadConstFloat(f64),
    LoadConstStr(u32),
    LoadVar(u16),
    CreateObject,

    SliceList,

    Add,
    Sub,
    Mul,
    Div,
    Mod,
    Concat,
    Pow,
    LAnd,
    LOr,

    CompG,
    CompL,
    CompEq,

    LoadMember,
    StoreMember,

    Call,

    Jump(u32),
    IfFalse(u32),

    Ret,

    Throw,

    PostEvent,
    WaitEvent,

    Spawn,

    WriteChannel,
    ReadChannel,

    Not,
    XOr,
    BAnd,
    BOr,
    LeftShift,
    RightShift,
    URightShift,
    Modulo,
    BCompliment,

    Dup,
    Consume,

    SwapTOS2WithTOS3
}

impl Instruction {
    pub fn decode(bytes: &[u8]) -> Option<(Instruction, usize)> {
        match bytes[0] {
            0x0 => Some((Store(Instruction::param_to_u16(bytes)), 3 as usize)),
            0x1 => Some((LoadConstInt(Instruction::param_to_i64(bytes)), 9 as usize)),
            0x2 => Some((LoadConstFloat(Instruction::param_to_f64(bytes)), 9 as usize)),
            0x3 => Some((LoadConstStr(Instruction::param_to_u32(bytes)), 5 as usize)),
            0x4 => Some((LoadVar(Instruction::param_to_u16(bytes)), 3 as usize)),
            0x5 => Some((CreateObject, 1 as usize)),
            0x6 => Some((SliceList, 1 as usize)),
            0x7 => Some((Add, 1 as usize)),
            0x8 => Some((Sub, 1 as usize)),
            0x9 => Some((Mul, 1 as usize)),
            0xA => Some((Div, 1 as usize)),
            0xB => Some((Mod, 1 as usize)),
            0xC => Some((Concat, 1 as usize)),
            0xD => Some((Pow, 1 as usize)),
            0xE => Some((LAnd, 1 as usize)),
            0xF => Some((LOr, 1 as usize)),
            0x10 => Some((CompG, 1 as usize)),
            0x11 => Some((CompL, 1 as usize)),
            0x12 => Some((CompEq, 1 as usize)),
            0x13 => Some((LoadMember, 1 as usize)),
            0x14 => Some((StoreMember, 1 as usize)),
            0x15 => Some((Call, 1 as usize)),
            0x16 => Some((Jump(Instruction::param_to_u32(bytes)), 5 as usize)),
            0x17 => Some((IfFalse(Instruction::param_to_u32(bytes)), 5 as usize)),
            0x18 => Some((Ret, 1 as usize)),
            0x19 => Some((Throw, 1 as usize)),
            0x1A => Some((PostEvent, 1 as usize)),
            0x1B => Some((WaitEvent, 1 as usize)),
            0x1C => Some((Spawn, 1 as usize)),
            0x1D => Some((WriteChannel, 1 as usize)),
            0x1E => Some((ReadChannel, 1 as usize)),
            0x1F => Some((Not, 1 as usize)),
            0x20 => Some((XOr, 1 as usize)),
            0x21 => Some((BAnd, 1 as usize)),
            0x22 => Some((BOr, 1 as usize)),
            0x23 => Some((LeftShift, 1 as usize)),
            0x24 => Some((RightShift, 1 as usize)),
            0x25 => Some((URightShift, 1 as usize)),
            0x26 => Some((Modulo, 1 as usize)),
            0x27 => Some((BCompliment, 1 as usize)),
            0x28 => Some((Dup, 1 as usize)),
            0x29 => Some((Consume, 1 as usize)),
            0x2A => Some((SwapTOS2WithTOS3, 1 as usize)),
            _ => {
                println!("bad opcode {}", bytes[0]);
                None
            }
        }
    }

    fn param_to_u16(data: &[u8]) -> u16 {
        let sized_data = [data[1], data[2]];
        unsafe {
            mem::transmute(sized_data)
        }
    }

    fn param_to_u32(data: &[u8]) -> u32 {
        let sized_data = [data[1], data[2], data[3], data[4]];
        let foo = unsafe {
            mem::transmute(sized_data)
        };
        foo
    }

    fn param_to_i64(data: &[u8]) -> i64 {
        let sized_data = [data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]];
        unsafe {
            mem::transmute(sized_data)
        }
    }


    fn param_to_f64(data: &[u8]) -> f64 {
        let sized_data = [data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]];
        unsafe {
            mem::transmute(sized_data)
        }
    }
}
