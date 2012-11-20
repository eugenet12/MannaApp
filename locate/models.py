from django.db import models

# Create your models here.
class CheckIn(models.Model):
    subtext = models.TextField()
    timestamp = models.DateField(auto_now_add=True)
    sourceCheckIn = models.ForeignKey('CheckIn')

class Venue(models.Model):
    name = models.CharField(max_length=30)
    latitude = models.IntegerField()
    longitude = models.IntegerField()
