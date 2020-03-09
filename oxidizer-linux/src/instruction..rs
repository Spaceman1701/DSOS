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
    Consume
}

//impl Instruction {
//    fn value(&self) -> u8 {
//        match *self {
//            Instruction::Store(_) => 0,
//            Instruction::LoadConstInt(_) => 1,
//            Instruction::LoadConstFloat(_) => 2,
//            Instruction::LoadConstStr(_) => 3,
//            Instruction::LoadVar(_) => 4,
//            Instruction::CreateObject => 5,
//            Instruction::SliceList => 6,
//            Instruction::Add => 7,
//            Instruction::Sub => 8,
//            Instruction::Mul => 9,
//            Instruction::Div => 10,
//            Instruction::Mod => 11,
//            Instruction::Concat => 12,
//            Instruction::Pow => 13,
//            Instruction::LAnd => 14,
//            Instruction::LOr => 15,
//            Instruction::CompG => 16,
//            Instruction::CompL => 17,
//            Instruction::CompEq => 18,
//            Instruction::LoadMember => 19,
//            Instruction::StoreMember => 20,
//            Instruction::Call => 21,
//            Instruction::Jump(_) => 22,
//            Instruction::IfFalse(_) => 23,
//            Instruction::Ret => 24,
//            Instruction::Throw => 25,
//            Instruction::PostEvent => 26,
//            Instruction::WaitEvent => 27,
//            Instruction::Spawn => 28,
//            Instruction::WriteChannel => 29,
//            Instruction::ReadChannel => 30,
//            Instruction::Not => 31,
//            Instruction::XOr => {}
//            Instruction::BAnd => {}
//            Instruction::BOr => {}
//            Instruction::LeftShift => {}
//            Instruction::RightShift => {}
//            Instruction::URightShift => {}
//            Instruction::Modulo => {}
//            Instruction::BCompliment => {}
//            Instruction::Dup => {}
//            Instruction::Consume => {}
//        }
//    }
//}