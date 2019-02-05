# Create your views here.
# Listar y crear
from rest_framework import generics
from sistemas.serializers import *


class PacienteAPI(generics.ListCreateAPIView):
    queryset = Paciente.objects.all()
    serializer_class = PacienteSerializado


#  Actualizar
class PacienteUpdate(generics.UpdateAPIView):
    queryset = Paciente.objects.all()
    lookup_field = 'id'
    serializer_class = PacienteSerializado


# Borrar
class PacienteDelete(generics.DestroyAPIView):
    queryset = Paciente.objects.all()
    lookup_field = 'id'
    serializer_class = PacienteSerializado
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)


#  Para la Medicamento
class MedicamentoGetPost(generics.ListCreateAPIView):
    serializer_class = MedicamentoSerializada

    # Redefinir el queryset
    def get_queryset(self):
        queryset = Medicamento.objects.all().order_by('-pk')
        # Parametro de consulta para filtrar Medicamento por paciente
        paciente = self.request.query_params.get('so', None)
        if paciente is not None:
            queryset = queryset.filter(sistemaOperativo__id=paciente)
        return queryset


#  Actualizar
class MedicamentoUpdate(generics.UpdateAPIView):
    queryset = Medicamento.objects.all()
    lookup_field = 'id'
    serializer_class = MedicamentoSerializada


# Borrar
class MedicamentoDelete(generics.DestroyAPIView):
    queryset = Medicamento.objects.all()
    lookup_field = 'id'
    serializer_class = MedicamentoSerializada
    http_method_names = ["delete"]
    allowed_methods = ('DELETE',)
