package service;

import model.MediaEntry;

public interface MediaEntryService {

    void createMedia();

    void updateMedia();

    void deleteMedia(MediaEntry mediaEntry);
}
