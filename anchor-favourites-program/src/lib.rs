use anchor_lang::prelude::*;

declare_id!("CBTLZYbZ8HVETNNxx1uTwdY4WEX54CmorhMNjqxbYKRJ");

pub const ANCHOR_DISCRIMINATOR_SIZE: usize = 8; //to specify the type of account, entry point for instructions, space every anchor account needs at minimum

#[program] //favorites module now becomes a solana program
pub mod favorites {
    use super::*;

    pub fn set_favorites(
        context: Context<SetFavorites>,
        number: u64,
        color: String,
        hobbies: Vec<String>,
    ) -> Result<()> {
        msg!("Greetings from {}", context.program_id);
        let user_pubkey = context.accounts.user.key();

        msg!(
            "User {user_pubkey}'s favorite number is {number}, favorite color is {color} and hobbies are {hobbies:?}"
        );

        context.accounts.favorites.set_inner(Favorites {
            number,
            color,
            hobbies,
        });

        Ok(())
    }
}

#[account]
//marks a struct as an on-chain account. Anchor handles serialization, discriminator, and memory allocation.
#[derive(InitSpace)] //total space used by all the items inside
pub struct Favorites {
    pub number: u64,

    #[max_len(50)]
    pub color: String,

    #[max_len(5, 50)]
    pub hobbies: Vec<String>,
}

#[derive(Accounts)]
pub struct SetFavorites<'info> {
    #[account(mut)]
    pub user: Signer<'info>,

    #[account(
        init_if_needed,
        payer = user,
        space = ANCHOR_DISCRIMINATOR_SIZE + Favorites::INIT_SPACE,
        seeds = [b"favorites", user.key().as_ref()],
        bump
    )]
    pub favorites: Account<'info, Favorites>,

    pub system_program: Program<'info, System>,
}
