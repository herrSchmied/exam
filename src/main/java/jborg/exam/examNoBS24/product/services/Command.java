package jborg.exam.examNoBS24.product.services;

import org.springframework.http.ResponseEntity;

public interface Command <O, I>
{
	public ResponseEntity<O> execute(I input);
}
