package services;

import entities.Publisher;
import repositories.PublisherRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PublisherService implements EntityService<Publisher> {

    private PublisherRepository publisherRepository;

    public PublisherService() {
        this.publisherRepository = new PublisherRepository();
    }

    @Override
    public void create(Publisher publisher) {
        if(!existsPublisherWithName(publisher.getName()) && hasCorrectPhoneNumber(publisher.getPhoneNumber())) publisherRepository.create(publisher);
    }
    private boolean existsPublisherWithName(String name){
        if(publisherRepository.getPublisherByName(name) != null){
            throw new IllegalArgumentException("The publisher with this name already exists");
        }
        return false;
    }
    private boolean hasCorrectPhoneNumber(String phoneNumber){
        String phoneNumberPattern = "\\+48 \\d{3}-\\d{3}-\\d{3}";
        if (!phoneNumber.matches(phoneNumberPattern)) {
            throw new IllegalArgumentException("Invalid phone number format. The correct format is: +48 XXX-XXX-XXX");
        }
        return true;
    }
    @Override
    public void update(Publisher publisher) {
        if (exists(publisher) && hasCorrectPhoneNumber(publisher.getPhoneNumber())) publisherRepository.update(publisher);
    }
    @Override
    public void delete(int id) {
        Publisher publisher = getById(id);
        if (exists(publisher) && !hasBooks(publisher)) publisherRepository.delete(publisher);
    }
    private boolean hasBooks(Publisher publisher){
        if(!publisher.getBooks().isEmpty()) throw new IllegalArgumentException("The publisher is assigned to the books");
        return false;
    }
    public List<Publisher> getAll() {
        List<Publisher> publishers = publisherRepository.getAll();
        if (publishers == null) {
            return new ArrayList<>();
        }
        return publishers;
    }

    @Override
    public <U> U getReference(Field field, int id) {
        return null;
    }

    @Override
    public Publisher getById(int id) {
        return publisherRepository.findById(id);
    }

    public boolean exists(Publisher publisher) {
        if (publisher != null) return true;
        else throw new IllegalArgumentException("Publisher does not exist.");
    }
}
