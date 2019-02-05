from .views import *
from django.urls.conf import path

urlpatterns = [
    # Urls para Paciente (Padre)
    path('api/', PacienteAPI.as_view(), name="so_get"),
    path('api/<int:id>/update', PacienteUpdate.as_view(), name="so_put"),
    path('api/<int:id>/delete', PacienteDelete.as_view(), name="so_delete"),
    # Urls para Medicamento (Hijo)
    path('api/app/', MedicamentoGetPost.as_view(), name="app_get"),
    path('api/app/<int:id>/update', MedicamentoUpdate.as_view(), name="app_put"),
    path('api/app/<int:id>/delete', MedicamentoDelete.as_view(), name="app_delete"),
]
