from django.contrib import admin
from django.urls import path
from calculator.views import emi_calculator

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/emi/', emi_calculator, name='emi_calculator'),
]
