import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/*
 * *Nombre de los programadores: Dario Verdezoto
 * *Materia: Programacion 2
 * *Fecha: 26/05/2024
 * *Detalle: Cookies Creacion
 * *Version: 2
 */
//SIEMPRE EL PATH
@WebServlet("/Servlet")
public class Servlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //CREAR UNA VARIABLE DE NUEVO USUARIO
        //PARA SABER SI ES UN USUARIO NUVEO
        //O ANTIGUO
        Boolean nuevoUsuario=true;
        //VAMOS A TENER EL ARREGLO DE LAS COOKIES
        Cookie[] cookies = request.getCookies();
        int cookiecont = 0;
        //VAMOS A VALIDAR SI EXISTE O NO LA COOKIE
        if (cookies != null) {
            //VA A RECORRER TODAS LAS COOKIES DE INICIO A FIN
            for (Cookie cookie : cookies) {
                //EN ESTA CONDICION SI ES UN VISITANTE RECURRENTE VA A RETORNAR UN FALSE
                if (cookie.getName().equals("visitanteRecurrente") && cookie.getValue().equals("si")) {
                    nuevoUsuario=false;


                }
                //DENTRO DEL BUCLE DE VALIDACION DE COOKIE
                //OBTENCION DEL NOMBRE DE LA COOKIE
                //Y COMPARARLO PARA QUE LA CONDICION SEA TRUE
                if (cookie.getName().equals("cookiecont")) {
                    //EL VALOR OBTENIDO ES DE TIPO STRING
                    //PERO SE LO CONVIERTE CON EL INTEGER
                    //Y LO ASIGNA A LA VARIABLE
                    cookiecont = Integer.parseInt(cookie.getValue());

                }
            }
        }
        //INCREMENTAR EL CONTADOR DE LAS COOKIES
        cookiecont++;

        //MENSAJE PARA EL USUARIO
        String mensaje=null;

        if (nuevoUsuario) {
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje="Gracias or ingresar al sitio por primera vez";
        }else {

            mensaje="Gracias por visitar mi sitio nuevamente";
        }
        // VA A ACTUALIZAR EL CONTADOR DE LAS COOKIES
        Cookie cont = new Cookie("cookiecont", Integer.toString(cookiecont));
        //CON ESTE SET.PATH ESTARA ENTODO MOMENTO
        //DISPONIBLE LA COOKIE DENTRO DE LA APLICACION
        cont.setPath("/");
        response.addCookie(cont);
        // CONFIGURAR PARA QUE LA RESPUETA SEA HTML
        response.setContentType("text/html;charset=UTF-8");
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.printf("<meta charset=\"utf-8\">");
        out.printf("<title> Hola Mundo </title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>"+nuevoUsuario+"</h1>");
        out.print("<h1>"+cookies+"</h1>");
        out.print("<h1>"+mensaje+"</h1>");
        out.print("<h1>Contador de Visitas: "+cookiecont+"</h1>");
        out.print("</body>");
        out.print("</html>");

    }
}
//REALIZAR UN CONTEO PARA LAS COOKIES Y MOSTRAR EN PANTALLA
