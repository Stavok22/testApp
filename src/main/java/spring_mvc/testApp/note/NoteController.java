package spring_mvc.testApp.note;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/note")
@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getListAllNotes() {
        ModelAndView result = new ModelAndView("list");
        result.addObject("listNotes", noteService.listAll().values());
        return result;
    }

    @GetMapping("/delete")
    public ModelAndView deleteNote(Note note) {
        ModelAndView result = new ModelAndView("redirect:/note/list");
        noteService.deleteById(note.getId());
        return result;
    }

    @GetMapping("/create")
    public ModelAndView getCreateView() {
        ModelAndView result = new ModelAndView("create");
        result.addObject("createNote", new Note());
        return result;
    }

    @PostMapping("/create")
    public ModelAndView createNote(Note note) {
        noteService.add(note);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView getEditView(@RequestParam("id") Long id) {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("editNote", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public ModelAndView editNote(Note note) {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/search")
    public ModelAndView searchNote(String pattern) {
        ModelAndView result=new ModelAndView("list");
        result.addObject("listNotes", noteService.searchNote(pattern));
        return result;
    }
}
