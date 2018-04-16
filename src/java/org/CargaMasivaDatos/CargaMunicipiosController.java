/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.CargaMasivaDatos;

//import edu.crud.util.MessageUtil;
import com.modelo.dao.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author David
 */
@Named(value = "cargaMunicipiosController")
@ViewScoped
public class CargaMunicipiosController implements Serializable{

    @Inject
    private FileUploadController fuc;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    /**
     * Creates a new instance of CargaUsuarioController
     */
    public CargaMunicipiosController() {
    }
    
    public String cargarUsuarios(){
        //System.out.println("hdjsadkasjdjsaljdajkld");
        fuc.setNameFileUpload("datos");
        if(fuc.uploadFiles()){
            try {
                String fileDatos = FacesContext.getCurrentInstance().getExternalContext().getRealPath("")
                        + fuc.getPathUploadFile() + fuc.getNameFileUpload();
                String d = fileDatos.replaceAll("\\\\", "/");
                System.out.println("dhsjakhdjhajdhasjhdj - " + fileDatos);
                System.out.println("dhsjakhdjhajdhasjhdj - " + d);
                usuarioFacadeLocal.loadUsuarios(d);
            } catch (Exception e) {
                e.printStackTrace();;
            }
            return "";
        } else{
            
            return "";
        }
    }
}
