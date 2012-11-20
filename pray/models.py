from django.db import models

# Create your models here.
class Person(models.Model):
    name = models.CharField(max_length=30)
    year = models.CharField(max_length=4)
    email = models.CharField(max_length=30) 

    def __unicode__(self):
        return self.name

class Prayer(models.Model):
    kind = models.IntegerField()
    message = models.TextField()
    responses = models.IntegerField()
    origin = models.ForeignKey('Person')

    def __unicode__(self):
        return self.kind

class Replies(models.Model):
    message = models.TextField()
    associatedPrayer = models.ForeignKey('Prayer')
    associatedPerson = models.ForeignKey('Person')

    def __unicode__(self):
        return self.associatedPerson

class Attachement(models.Model):
    kind = models.IntegerField()
    content = models.CharField(max_length=500)
    associatedReply = models.ForeignKey('Replies')

    def __unicode__(self):
        return self.kind
