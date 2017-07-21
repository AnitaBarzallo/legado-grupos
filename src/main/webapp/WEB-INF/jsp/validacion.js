/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("#formgrupo").bootstrapValidator({  
        fields: {
            nombreGrupo: {
                validators: {
                    notEmpty: { message: "El nombre del grupo es requerido"}
                }
            },
        }
    });
});
