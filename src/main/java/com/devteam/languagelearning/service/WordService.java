package com.devteam.languagelearning.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.languagelearning.model.User;
import com.devteam.languagelearning.model.Word;
import com.devteam.languagelearning.persistence.WordRepository;

@Service
public class WordService {
	
	@Autowired
	private WordRepository wordRepository;

	@Autowired
	private UserService userService;

	public List<Word> getAllWords() {
		return wordRepository.findAll();
	}

	public Optional<Word> findById(long id) {
		return this.wordRepository.findById(id);
	}

	public List<Word> getWordsByUser(long user_id) {
		Optional<User> optionalUser = userService.findById(user_id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
		
		return this.wordRepository.findByUser(user);
		} else {
			return null;
		}
	}
	

	public Word deleteWord(long id) throws NoSuchElementException {
		Word word = wordRepository.findById(id).orElseThrow();
		wordRepository.deleteById(id);
		return word;
	}
}
