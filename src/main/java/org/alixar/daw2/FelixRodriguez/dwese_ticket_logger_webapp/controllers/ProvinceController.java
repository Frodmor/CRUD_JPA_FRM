package org.alixar.daw2.FelixRodriguez.dwese_ticket_logger_webapp.controllers;

import jakarta.validation.Valid;
import org.alixar.daw2.FelixRodriguez.dwese_ticket_logger_webapp.DAO.ProvinceDAO;
import org.alixar.daw2.FelixRodriguez.dwese_ticket_logger_webapp.DAO.RegionDAO;
import org.alixar.daw2.FelixRodriguez.dwese_ticket_logger_webapp.entities.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProvinceDAO provinceDAO;

    @Autowired
    private RegionDAO regionDAO;

    @GetMapping
    public String listProvinces(Model model) {
        logger.info("Solicitando la lista de todas las provincias...");
        List<Province> listProvinces = null;
        try {
            listProvinces = provinceDAO.listAllProvinces();
            logger.info("Se han cargado {} provincias.", listProvinces != null ? listProvinces.size() : 0);
        } catch (DataAccessException e) {
            logger.error("Error al listar las provincias: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las provincias.");
        }
        model.addAttribute("listProvinces", listProvinces);
        return "province";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Mostrando formulario para nueva provincia.");
        model.addAttribute("province", new Province());
        try {
            model.addAttribute("regions", regionDAO.listAllRegions());
        } catch (DataAccessException e) {
            logger.error("Error al cargar regiones para el formulario: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al cargar la lista de regiones.");
        }
        return "province-form";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        logger.info("Mostrando formulario de edición para la provincia con ID {}", id);
        Province province = null;
        try {
            province = provinceDAO.getProvinceById(id);
            if (province == null) {
                logger.warn("No se encontró la provincia con ID {}", id);
            }
            model.addAttribute("regions", regionDAO.listAllRegions());
        } catch (DataAccessException e) {
            logger.error("Error al obtener la provincia o regiones con ID {}: {}", id, e.getMessage());
            model.addAttribute("errorMessage", "Error al cargar los datos para editar la provincia.");
        }
        model.addAttribute("province", province);
        return "province-form";
    }

    @PostMapping("/insert")
    public String insertProvince(@Valid @ModelAttribute("province") Province province,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Insertando nueva provincia con código {}", province.getCode());
        try {
            if (result.hasErrors()) {
                model.addAttribute("regions", regionDAO.listAllRegions());
                return "province-form";
            }
            if (provinceDAO.existsProvinceByCode(province.getCode())) {
                logger.warn("El código de la provincia {} ya existe.", province.getCode());
                model.addAttribute("regions", regionDAO.listAllRegions());
                model.addAttribute("errorMessage", "El código de la provincia ya existe.");
                return "province-form";
            }
            provinceDAO.insertProvince(province);
            logger.info("Provincia {} insertada con éxito.", province.getCode());
            redirectAttributes.addFlashAttribute("successMessage", "Provincia insertada con éxito.");
        } catch (DataAccessException e) {
            logger.error("Error al insertar la provincia {}: {}", province.getCode(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al insertar la provincia.");
        }
        return "redirect:/provinces";
    }

    @PostMapping("/update")
    public String updateProvince(@Valid @ModelAttribute("province") Province province,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Actualizando provincia con ID {}", province.getId());
        try {
            if (result.hasErrors()) {
                model.addAttribute("regions", regionDAO.listAllRegions());
                return "province-form";
            }
            int provinceId = province.getId() != null ? province.getId().intValue() : 0;
            if (provinceDAO.existsProvinceByCodeAndNotId(province.getCode(), provinceId)) {
                logger.warn("El código de la provincia {} ya existe para otra provincia.", province.getCode());
                model.addAttribute("regions", regionDAO.listAllRegions());
                model.addAttribute("errorMessage", "El código de la provincia ya existe para otra provincia.");
                return "province-form";
            }
            provinceDAO.updateProvince(province);
            logger.info("Provincia con ID {} actualizada con éxito.", province.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Provincia actualizada con éxito.");
        } catch (DataAccessException e) {
            logger.error("Error al actualizar la provincia con ID {}: {}", province.getId(), e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar la provincia.");
        }
        return "redirect:/provinces";
    }

    @PostMapping("/delete")
    public String deleteProvince(@RequestParam("id") int id,
                                 RedirectAttributes redirectAttributes) {
        logger.info("Eliminando provincia con ID {}", id);
        try {
            provinceDAO.deleteProvince(id);
            logger.info("Provincia con ID {} eliminada con éxito.", id);
            redirectAttributes.addFlashAttribute("successMessage", "Provincia eliminada con éxito.");
        } catch (DataAccessException e) {
            logger.error("Error al eliminar la provincia con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la provincia.");
        }
        return "redirect:/provinces";
    }
}
