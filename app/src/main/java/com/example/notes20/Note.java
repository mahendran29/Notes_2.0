package com.example.notes20;

import com.google.firebase.firestore.Exclude;

public class Note
{
    private String title;
    private String description;
    private String documentID;

    Note()
    {

    }

    Note(String title,String description)
    {
        this.title=title;
        this.description=description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }


    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
}
