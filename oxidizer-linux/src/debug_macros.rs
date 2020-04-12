#[macro_use]
pub mod vm_debug {
    #[cfg(feature = "ox_debug")]
    #[macro_export]
    macro_rules! vm_debug {
    ($($args: expr),*) => (
            println!($($args),*)
    );
}
    #[cfg(not(feature = "ox_debug"))]
    #[macro_export]
    macro_rules! vm_debug {
        ($($args: expr),*) => {}
    }
}
