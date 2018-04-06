/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo.converters;

import com.modelo.dao.MunicipioFacadeLocal;
import com.modelo.entidades.Municipio;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter(forClass = Municipio.class)
public class MunicipioConverter implements Converter{
    
    private MunicipioFacadeLocal municipioFacadeLocal;
    
    public MunicipioConverter(){
        municipioFacadeLocal = CDI.current().select(MunicipioFacadeLocal.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return municipioFacadeLocal.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Municipio) {
            return ((Municipio) value).getIdMunicipio().toString();
        }
        return "";

    }

}
