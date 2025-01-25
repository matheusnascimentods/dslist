package com.devsuperior.devlist.exception.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class GameNotFoundException extends EntityNotFoundException{
	public GameNotFoundException () { super("Game not found!"); }
}
