from django.contrib import admin
from pray.models import Prayer, Person

admin.site.register(Prayer, Person)
