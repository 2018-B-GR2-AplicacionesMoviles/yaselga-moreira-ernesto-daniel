from django.db import models


class Paciente(models.Model):
    nombre = models.CharField(max_length=20, null=False, blank=False)
    apellido = models.CharField(null=False, blank=False, max_length=8)
    fechaNacimiento = models.CharField(max_length=20, null=False, blank=False)
    hijos = models.IntegerField(null=False, blank=False)


class Medicamento(models.Model):
    nombre = models.CharField(max_length=20, null=False, blank=False)
    composicion = models.CharField(null=False, blank=False, max_length=8)
    fechaCaducidad = models.CharField(max_length=20, null=False, blank=False)
    gramosAingerir = models.CharField(null=False, blank=False, max_length=5)
    numeroPastillas = models.DecimalField(null=False, blank=False, decimal_places=2, max_digits=5)
    usadoPara = models.CharField(max_length=150, null=False, blank=False)
    codigo_barras = models.CharField(max_length=13, null=False, blank=False)
    paciente = models.ForeignKey(Paciente, on_delete=models.CASCADE)