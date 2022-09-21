
package com.argprog.portfolioweb.controller;

import com.argprog.portfolioweb.model.FormularioContacto;
import com.argprog.portfolioweb.service.IFormularioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormularioController {
    @Autowired
    private IFormularioService formServ;
    
    @PostMapping ("/nuevo/mensaje")
    public void agregarMensaje (@RequestBody FormularioContacto formCon){//cuerpo de la solicitud
        formServ.crearMensaje(formCon);
    }
    @GetMapping ("/ver/mensajes")
    @ResponseBody
    public List<FormularioContacto> verMensajes(){
        return formServ.verMensajes();
    }
    @DeleteMapping("/delete/mensaje/{id}")
    public void borrarMensaje(@PathVariable Long id){
       formServ.borrarMensaje(id);
    }
    @PutMapping("/modificar/mensaje/{id}")
    public FormularioContacto editarMensaje (@PathVariable Long id,
                                  @RequestParam ("nombre") String nuevoNombre,
                                  @RequestParam ("email") String nuevoEmail,
                                  @RequestParam ("mensaje") String nuevoMensaje,
                                  @RequestParam ("telefono") int nuevoTelefono){
      FormularioContacto formCon = formServ.editarMensaje(id);
      
      formCon.setNombre(nuevoNombre);
      formCon.setEmail(nuevoEmail);
      formCon.setTelefono(nuevoTelefono);
      formCon.setMensaje(nuevoMensaje);
      
      formServ.crearMensaje(formCon);
      
      return formCon;
    }
    
}
