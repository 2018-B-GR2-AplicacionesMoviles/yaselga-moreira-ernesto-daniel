from rest_framework import serializers
from .models import *


class PacienteSerializado(serializers.ModelSerializer):
    class Meta:
        model = Paciente
        fields = '__all__'


class MedicamentoSerializada(serializers.ModelSerializer):
    class Meta:
        model = Medicamento
        fields = '__all__'
