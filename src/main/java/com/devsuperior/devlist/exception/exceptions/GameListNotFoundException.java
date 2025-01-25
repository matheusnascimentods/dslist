package com.devsuperior.devlist.exception.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class GameListNotFoundException extends EntityNotFoundException{
	public GameListNotFoundException () { super("List not found!"); }
}
