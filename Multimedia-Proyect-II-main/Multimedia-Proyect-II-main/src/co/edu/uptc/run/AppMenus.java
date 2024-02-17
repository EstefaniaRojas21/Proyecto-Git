package co.edu.uptc.run;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Util.CategoryManagement;
import Util.MoviesManagement;
import Util.SeriesManagement;
import Util.UserRegisteredManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.time.LocalTime;
import java.time.ZoneId;

import co.edu.uptc.model.Admin;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.MultimediaContent;
import co.edu.uptc.model.PlayList;
import co.edu.uptc.controller.AdminController;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.PlayListController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserRegisteredController;

public class AppMenus {
    private String[] adds = { "IF U ARE RICH AF, BUY THE NEW CIBERTRUK A$AP",
            "BUY THE NEW TESLA!!!! SAVE THE WORLD AGAISN POLLUTION",
            "IF U HAVE OPP BURN 'EM!!! SPECIAL OFFERTS",
            "IF U ARE TOXIC, LETS CREATE AN ACCOUNT IN X!!!",
            "YOUR MONEY IS SAVE WITH PAYPAL" };
    private Icon[] addsPics = {
            new ImageIcon(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\add1.png"),
            new ImageIcon(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\add2.png"),
            new ImageIcon(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\add3.png"),
            new ImageIcon(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\add4.png"),
            new ImageIcon(System.getProperty("user.dir")
                    + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\add5.png") };

    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");
    private UserRegisteredManagement um = new UserRegisteredManagement();
    private AdminController ac = new AdminController();
    private UserRegisteredController userRegisteredC = new UserRegisteredController();
    private PlayListController playListC = new PlayListController();
    private CategoryController categoryC = new CategoryController();
    private SubscriptionController subsC = new SubscriptionController();
    private MoviesManagement mm = new MoviesManagement();
    private SeriesManagement sm = new SeriesManagement();
    private CategoryManagement cm = new CategoryManagement();

    public AppMenus() {
        userRegisteredC.setAdmin(admin);
        ac.setAdmin(admin);
        admin.setMovies(mm.getMovies());
        admin.setSeries(sm.getSeries());
        userRegisteredC.setMovies(admin.getMovies());
        userRegisteredC.setSeries(admin.getSeries());
        ac.setListMovies(admin.getMovies());
        ac.setListSeries(admin.getSeries());
        playListC.setMovies(admin.getMovies());
        playListC.setSeries(admin.getSeries());
        categoryC.setMovies(admin.getMovies());
        categoryC.setSeries(admin.getSeries());

        playListC.setUserC(userRegisteredC);
        categoryC.setUserC(userRegisteredC);
    }

    // ----------------------------------------Principal
    // menu(0)------------------------------------------------------------------------//
    public int principalMenu(int op) {
        Icon principalMenuIcon = new ImageIcon(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\PrincipalMenu.png");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Welcome to NextFlix"), BorderLayout.NORTH);
        panel.add(new JLabel(principalMenuIcon), BorderLayout.CENTER);
        op = JOptionPane.showOptionDialog(null, panel, "Principal Menu", JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "LogIn", "Register", "Visitant", "Leave" }, null);
        switch (op) {
            case 0:
                op = 1;
                break;
            case 1:
                op = 2;
                break;
            case 2:
                op = 3;
                break;
            case 3:
                op = -1;
                break;
        }
        return op;

    }

    // ----------------------------------------LogIn
    // menu(1)------------------------------------------------------------------------//
    public int logInMenu(int op) {
        JPanel panelLogIn = new JPanel(new GridLayout(2, 2));
        JTextField user = new JTextField();
        JTextField password = new JTextField();
        panelLogIn.add(new JLabel("User: "));
        panelLogIn.add(user);
        panelLogIn.add(new JLabel("Password: "));
        panelLogIn.add(password);

        while (true) {
            op = JOptionPane.showOptionDialog(null, panelLogIn, "LogIn Menu", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE, null,
                    new Object[] { "LogIn", "Return" }, null);

            if (op == 0) {
                if (admin.couldLogIn(user.getText(), password.getText())) {
                    if (admin.getPassword().equals(password.getText())) {
                        op = 4;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin password incorrect");
                    }

                } else if (userRegisteredC.userFound(user.getText())) {
                    if (userRegisteredC.getCurrentuser().couldLogIn(user.getText(), password.getText())) {
                        if (userRegisteredC.getCurrentUser().getPassword().equals(password.getText())) {
                            op = 20;

                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "User Registered password incorrect");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not found!");
                }
            } else if (op == 1) {
                return 0;
            }
        }

        return op;
    }

    // ----------------------------------------Register
    // menu(2)------------------------------------------------------------------------//
    public int registerMenu(int op) {
        JPanel panelRegister = new JPanel(new GridLayout(10, 1));
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField id = new JTextField();
        JTextField password = new JTextField();
        JTextField passwordValidate = new JTextField();
        panelRegister.add(new JLabel("FirstName: "));
        panelRegister.add(firstName);
        panelRegister.add(new JLabel("LastName: "));
        panelRegister.add(lastName);
        panelRegister.add(new JLabel("ID: "));
        panelRegister.add(id);
        panelRegister.add(
                new JLabel("Create a password \n(minimum 8 characters, 1 uppercase, 1 special character, 1 number)"));
        panelRegister.add(password);
        panelRegister.add(new JLabel("Confirm your password"));
        panelRegister.add(passwordValidate);

        while (true) {

            op = JOptionPane.showOptionDialog(null, panelRegister, "Register Menu", JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Register", "Return" }, null);

            if (op == 0) {

                if (firstName.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$") && !(firstName.getText().isEmpty())) {
                    if (lastName.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$") && !(lastName.getText().isEmpty())) {
                        try {
                            if (id.getText().matches("\\d+") && Integer.parseInt(id.getText()) < 2147483647
                                    && Integer.parseInt(id.getText()) > 0) {
                                if (!userRegisteredC.idFound(Integer.parseInt(id.getText()))
                                        && !id.getText().equals(String.valueOf(admin.getId()))) {
                                    if (userRegisteredC.validatePassword(password.getText())) {
                                        if (password.getText().equals(passwordValidate.getText())) {
                                            break;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Passwords do not match");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Password invalid!" +
                                                "\n*minimum 8 characters" + "\n*minimun 1 uppercase"
                                                + "\n*minimun 1 special character" + "\n*minimun 1 number");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "ID invalid!" +
                                            "\n*ID already registered");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ID invalid!" +
                                        "\n*ID should contains only digits" + "\n*ID should be less than 2147483647");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID invalid!" +
                                    "\n*ID should contains only digits");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Last Name invalid!" +
                                "\n*Last Name shoud contains only characters" + "\n*Last Name should not be empty");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Name invalid!" +
                            "\n*First Name shoud contains only characters" + "\n*First Name should not be empty");
                }
            } else if (op == 1) {
                return 0;
            }
        }

        if (userRegisteredC.addUser(firstName.getText(), lastName.getText(), Integer.parseInt(id.getText()),
                password.getText())) {
            JOptionPane.showMessageDialog(null, "User registered successfully!" +
                    "\n User: " + firstName.getText() + id.getText() + "@uptc.edu.co" + "\nPassword: "
                    + password.getText());
        }

        return 0;
    }

    // ----------------------------------------Away
    // menu(3)------------------------------------------------------------------------//
    public int awayMenu(int op) {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        String[] movies = userRegisteredC.getMovieNames();
        JComboBox<String> moviesBox = new JComboBox<>(movies);

        String[] series = userRegisteredC.getSerieNames();
        JComboBox<String> seriesBox = new JComboBox<>(series);

        if (movies.length > 0) {
            panel.add(new JLabel("Movies:"));
            panel.add(moviesBox);
        } else {
            panel.add(new JLabel("Movies:"));
            panel.add(new JLabel("There are not movies added yet"));
        }

        if (series.length > 0) {
            panel.add(new JLabel("Series:"));
            panel.add(seriesBox);
        } else {
            panel.add(new JLabel("Series:"));
            panel.add(new JLabel("There are not series added yet"));
        }

        while (true) {
            op = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Movies Away Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] { "See movie information", "See serie information", "Create Account", "return" },
                    null);
            if (op == 0) {
                if (movies.length > 0) {
                    String[] aux = String.valueOf(moviesBox.getSelectedItem()).split("-");
                    JOptionPane.showMessageDialog(null,
                            "Name: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getName() +
                                    "\nDescription: "
                                    + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDescription() +
                                    "\nauthor: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getAuthor() +
                                    "\nDuration: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration());
                }
            } else if (op == 1) {
                if (series.length > 0) {
                    while (true) {
                        String[] aux = String.valueOf(seriesBox.getSelectedItem()).split("-");
                        String[] seasonsNames = ac.showSeasonsNames(Integer.parseInt(aux[1]));
                        JComboBox<String> seasonNamesBox = new JComboBox<>(seasonsNames);

                        JPanel seasonsPanel = new JPanel(new GridLayout(2, 2));
                        seasonsPanel.add(new JLabel("Serie name: "));
                        seasonsPanel.add(new JLabel(aux[0]));

                        if (seasonsNames.length > 0) {
                            seasonsPanel.add(new JLabel("Seasons:"));
                            seasonsPanel.add(seasonNamesBox);
                        } else {
                            seasonsPanel.add(new JLabel("Seasons:"));
                            seasonsPanel.add(new JLabel("There are not seasons available"));
                        }

                        op = JOptionPane.showOptionDialog(null, seasonsPanel, "Season Away Menu",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
                                new Object[] { "See Season", "return" }, seasonsPanel);

                        if (((String) (seasonNamesBox.getSelectedItem()) != null) && op == 0) {
                            while (true) {
                                String[] chapNames = ac.chapterNames(Integer.parseInt(aux[1]),
                                        (String) (seasonNamesBox.getSelectedItem()));
                                JComboBox<String> chapNamesBox = new JComboBox<>(chapNames);

                                JPanel chapPanel = new JPanel(new GridLayout(2, 2));
                                chapPanel.add(new JLabel("Season name: "));
                                chapPanel.add(new JLabel((String) (seasonNamesBox.getSelectedItem())));
                                if (chapNames.length > 0) {
                                    chapPanel.add(new JLabel("Chapters:"));
                                    chapPanel.add(chapNamesBox);
                                } else {
                                    chapPanel.add(new JLabel("Cahpters:"));
                                    chapPanel.add(new JLabel("There are not chapters available"));
                                }

                                op = JOptionPane.showOptionDialog(null, chapPanel, "Chapter Away Menu",
                                        JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
                                        new Object[] { "See Chapter", "return" }, null);
                                if ((((String) (chapNamesBox.getSelectedItem())) != null) && op == 0) {
                                    JOptionPane.showMessageDialog(null,
                                            "Name: " + (String) (chapNamesBox.getSelectedItem()) +
                                                    "\nDescription: " + ac.chapterForAwayMenu(Integer.parseInt(aux[1]),
                                                            (String) (seasonNamesBox.getSelectedItem()),
                                                            (String) (chapNamesBox.getSelectedItem())).getDescription()
                                                    +
                                                    "\nDuration: " + ac.chapterForAwayMenu(Integer.parseInt(aux[1]),
                                                            (String) (seasonNamesBox.getSelectedItem()),
                                                            (String) (chapNamesBox.getSelectedItem())).getDuration());
                                    return 3;
                                } else {
                                    break;
                                }

                            }
                        } else {
                            break;
                        }

                    }
                }
            } else if (op == 2) {
                return 2;
            } else if (op == 3) {
                break;
            }
        }
        return 0;
    }

    // ----------------------------------------Admin
    // menu(4)------------------------------------------------------------------------//
    public int administratorMenu(int op) {
        Icon adminMenuIcon = new ImageIcon(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\AdminMenu.png");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Welcome to Admin Menu"), BorderLayout.NORTH);
        panel.add(new JLabel(adminMenuIcon), BorderLayout.CENTER);
        op = JOptionPane.showOptionDialog(null, panel, "Admin Menu", JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[] { "Movies Management", "Series Management", "Category Management",
                        "Subscriptions Management", "return" },
                null);
        switch (op) {
            case 0:
                op = 5;
                break;
            case 1:
                op = 10;
                break;
            case 2:
                op = 35;
                break;
            case 3:
                op = 25;
                break;
            case 4:
                op = 0;
                break;
        }
        return op;
    }

    // ----------------------------------------Movies Management
    // menu(5)------------------------------------------------------------------------//
    public int moviesManagementMenu(int op) {
        op = JOptionPane.showOptionDialog(null, "Choose an action for the movie.", "Movies Menu",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "See", "Create", "Update", "Remove", "Return" }, null);
        switch (op) {
            case 0:
                op = 6;
                break;
            case 1:
                op = 7;
                break;
            case 2:
                op = 8;
                break;
            case 3:
                op = 9;
                break;
        }
        return op;
    }

    public int ShowMoviesMenu(int op) {
        if (ac.getMovies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no movies created yet");
        } else {
            String selectedMovieName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a movie",
                    "Movies",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ac.ShowListMovieNames().toArray(),
                    null);

            if (selectedMovieName != null) {
                String[] names = selectedMovieName.split("-");
                JOptionPane.showMessageDialog(null, ac.ShowMovies(Integer.parseInt(names[1])));
            }

        }
        return 5;
    }

    // ----------------------------------------Create Movies //
    // menu(7)------------------------------------------------------------------------//
    public int createMoviesMenu(int op) {

        JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();

        panel.add(new JLabel("Name of the movie:"));
        panel.add(nameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Duration:"));
        panel.add(durationField);
        while (true) {

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Create Movies Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String author = authorField.getText();
                String description = descriptionField.getText();
                String duration1 = durationField.getText();

                if (!name.isBlank() && !author.isBlank() && !description.isBlank()
                        && !duration1.isBlank()) {
                    if (ac.containCharacterSpecial(author) && ac.validateName(name) && ac.validateNumbers(duration1)
                            && ac.validateName(author) && ac.validateDescription(description) && name.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")) {
                        int duration = Integer.parseInt(duration1);
                        int confirmResult = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure all the data you entered is correct?\n" +

                                        "Name movie: " + name + "\nAuthor of movie: " + author
                                        + "\nDescription of movie: "
                                        + description +
                                        "\nDuration of movie: " + duration,
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION);

                        if (confirmResult == JOptionPane.YES_OPTION) {
                            ac.addMovie(name, author, description, duration);

                            JOptionPane.showMessageDialog(null, "The movie was added successfully!");
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "unsaved data");
                        }
                    } else {

                        if (!ac.validateNumbers(duration1)) {
                            JOptionPane.showMessageDialog(null, "Duration invalid");
                        } else if (!ac.validateName(author)) {
                            JOptionPane.showMessageDialog(null,
                                    "invalid name of author must have at least three letters");
                        } else if (!ac.validateDescription(description)) {
                            JOptionPane.showMessageDialog(null,
                                    "Invalid description must have a minimum of five letters");
                        } else if (!ac.validateName(name)) {
                            JOptionPane.showMessageDialog(null, "Invalid movie name  must have at least three letters");
                        } else if (!ac.containCharacterSpecial(author)) {
                            JOptionPane.showMessageDialog(null, "The author's name cannot have special characters");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must enter all the data");
                }
            } else {
                break;
            }
        } // Cierre While

        return 5;
    }

    // ----------------------------------------Update Movies
    // menu(8)------------------------------------------------------------------------//
    public int updateMoviesMenu(int op) {
        String selectedMovieName = "";
        if (ac.getMovies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no movies created yet");
        } else {
            selectedMovieName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a movie",
                    "Update " + selectedMovieName + " movie",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ac.ShowListMovieNames().toArray(),
                    null);

            if (selectedMovieName != null) {
                String[] names = selectedMovieName.split("-");
                int selectedMovie = ac.movieFound(Integer.parseInt(names[1]));
                Movie movieToUpdate = new Movie(ac.getMovies().get(selectedMovie).getId(),
                        ac.getMovies().get(selectedMovie).getName(),
                        ac.getMovies().get(selectedMovie).getAuthor(),
                        ac.getMovies().get(selectedMovie).getDescription(),
                        ac.getMovies().get(selectedMovie).getDuration());

                JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

                JTextField nameField = new JTextField(ac.getMovies().get(selectedMovie).getName());
                JTextField authorField = new JTextField(ac.getMovies().get(selectedMovie).getAuthor());
                JTextField descriptionField = new JTextField(ac.getMovies().get(selectedMovie).getDescription());
                JTextField durationField = new JTextField();
                durationField.setText(Integer.toString(ac.getMovies().get(selectedMovie).getDuration()));

                panel.add(new JLabel("Name of the movie:"));
                panel.add(nameField);
                panel.add(new JLabel("Author:"));
                panel.add(authorField);
                panel.add(new JLabel("Description:"));
                panel.add(descriptionField);
                panel.add(new JLabel("Duration:"));
                panel.add(durationField);

                while (true) {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            panel,
                            "Update " + selectedMovieName + " movie",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        // Obtener los nuevos valores de los campos de texto
                        String newName = nameField.getText();
                        String newAuthor = authorField.getText();
                        String newDescription = descriptionField.getText();
                        String duration1 = durationField.getText();

                        if (!newName.isBlank() && !newAuthor.isBlank() && !newDescription.isBlank()
                                && !duration1.isBlank() && newName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")) {
                            if (ac.containCharacterSpecial(newAuthor) && ac.validateName(newName)
                                    && ac.validateNumbers(duration1) && ac.validateName(newAuthor)
                                    && ac.validateDescription(newDescription)) {

                                int newDuration = Integer.parseInt(duration1);
                                int confirmResult = JOptionPane.showConfirmDialog(
                                        null,
                                        "Are you sure about these changes?\n" +
                                                "Name movie: " + newName + "\nAuthor of movie: " + newAuthor
                                                + "\nDescription of movie: "
                                                + newDescription +
                                                "\nDuration of movie: " + newDuration,
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION);

                                if (confirmResult == JOptionPane.YES_OPTION) {

                                    ac.modifyMovies(movieToUpdate, newDescription, newName, newDuration, newAuthor,
                                            Integer.parseInt(names[1]));

                                    JOptionPane.showMessageDialog(null, "The movie was modified successfully!");
                                    break;
                                } else {
                                    System.out.println("unsaved data");
                                }
                            } else {
                                if (!ac.validateNumbers(duration1)) {
                                    JOptionPane.showMessageDialog(null, "Duration invalid");
                                } else if (!ac.validateName(newAuthor)) {
                                    JOptionPane.showMessageDialog(null,
                                            "invalid name of author must have at least three letters");
                                } else if (!ac.validateDescription(newDescription)) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid description must have a minimum of five letters");
                                } else if (!ac.validateName(newName)) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid movie name  must have at least three letters");
                                } else if (!ac.containCharacterSpecial(newAuthor)) {
                                    JOptionPane.showMessageDialog(null,
                                            "The author's name cannot have special characters");
                                }

                            }
                        } 
                    } else {
                        break;
                    }
                }
            }
        } // cierra while
        return 5;
    }

    // ----------------------------------------Delete Movies
    // menu(9)------------------------------------------------------------------------//

    public int deleteMovieMenu(int op) {
        if (ac.getMovies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no movies created yet");
        } else {
            String selectedMovieName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a movie",
                    "Movies",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ac.ShowListMovieNames().toArray(),
                    null);

            if (selectedMovieName != null) {
                String[] aux = selectedMovieName.split("-");
                int confirmResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to the delete a movie " + selectedMovieName + " ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    ac.deleteMovie(Integer.parseInt(aux[1]));
                    for (UserRegistered user : userRegisteredC.getAllUsers()) {
                        for (PlayList playL : user.getplayList()) {
                            for (Movie m : playL.getMovies()) {
                                if (m.getId() == Integer.parseInt(aux[1])) {
                                    playL.removeMovie(m);
                                    break;
                                }
                            }
                        }
                    }
                    for (Category c : categoryC.getCategories()) {
                        for (Movie m : c.getMovies()) {
                            if (m.getId() == Integer.parseInt(aux[1])) {
                                c.removeMovie(m);
                                break;
                            }
                        }
                    }
                    um.updateUsers(userRegisteredC.getAllUsers());
                    cm.updateCategories(categoryC.getCategories());
                    JOptionPane.showMessageDialog(null, "The movie was deleted successfully!");
                } else {

                }
            }

        }
        return 5;
    }

    // ----------------------------------------Series Management
    // menu(10)------------------------------------------------------------------------//
    public int SeriesManagementMenu(int op) {
        op = JOptionPane.showOptionDialog(null, "Choose an option to do with a serie", "Admin Menu",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "See Serie", "Create Serie", "Update Serie", "Remove Serie", "Return" }, null);
        switch (op) {
            case 0:
                op = 12;
                break;
            case 1:
                op = 11;
                break;
            case 2:
                op = 13;
                break;
            case 3:
                op = 14;
                break;
        }
        return op;
    }

    // ----------------------------------------Create Series
    // //menu(11)------------------------------------------------------------------------//
    public int createSerieMenu(int op) {

        JPanel panel = new JPanel(new GridLayout(3, 3, 2, 2));

        JTextField nameField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField descriptionField = new JTextField();

        panel.add(new JLabel("Name of the Serie:"));
        panel.add(nameField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        while (true) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Create Series Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                String author = authorField.getText().trim();
                String description = descriptionField.getText().trim();
                if (!name.isBlank() && !author.isBlank() && !description.isBlank() && ac.validateName(name)
                        && ac.validateDescription(description) && ac.containCharacterSpecial(author)
                        && ac.validateName(author) && ac.containCharacterSpecial(name)) {

                    JPanel panel2 = new JPanel(new GridLayout(4, 4, 2, 2));

                    JTextField seasonNameField = new JTextField();
                    JTextField nameChapterField = new JTextField();
                    JTextField descriptionChapterField = new JTextField();
                    JTextField durationChapterField = new JTextField();

                    panel2.add(new JLabel("Season name:"));
                    panel2.add(seasonNameField);
                    panel2.add(new JLabel("Name of the chapter:"));
                    panel2.add(nameChapterField);
                    panel2.add(new JLabel("Description of the chapter:"));
                    panel2.add(descriptionChapterField);
                    panel2.add(new JLabel("Duration of the chapter:"));
                    panel2.add(durationChapterField);

                    while (true) {
                        int result2 = JOptionPane.showConfirmDialog(
                                null,
                                panel2,
                                "Create chapter Menu",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                        if (result2 == JOptionPane.OK_OPTION) {
                            String seasonName = seasonNameField.getText().trim();
                            String nameChapter = nameChapterField.getText().trim();
                            String descriptionChapter = descriptionChapterField.getText().trim();
                            String durationChapter1 = durationChapterField.getText().trim();

                            if (!nameChapter.isBlank() && !descriptionChapter.isBlank()
                                    && !String.valueOf(durationChapter1).isBlank() && !seasonName.isBlank()
                                    && ac.validateName(nameChapter) && ac.validateName(seasonName)
                                    && ac.validateDescription(descriptionChapter)
                                    && ac.validateNumbers(durationChapter1)
                                    && ac.validarSinCharacterSpecial(seasonName)) {

                                int durationChapter = Integer.parseInt(durationChapter1);
                                int confirmResult = JOptionPane.showConfirmDialog(
                                        null,
                                        "Are you sure all the data you entered is correct?\nName serie: " + name
                                                + "\nAuthor of serie: " + author + "\nDescription of serie: "
                                                + description + "\nSeason name: "
                                                + seasonName + "\nName chapter: " + nameChapter
                                                + "\nDescription of chapter: " + descriptionChapter
                                                + "\nDuration of chapter: " + durationChapter,
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION);

                                if (confirmResult == JOptionPane.YES_OPTION) {
                                    ac.addSerie(name, author, description,
                                            ac.createSeasons(seasonName,
                                                    ac.createChapter(nameChapter, descriptionChapter,
                                                            durationChapter)));

                                    while (true) {

                                        int confirmResult1 = JOptionPane.showConfirmDialog(
                                                null,
                                                "Do you want to add another chapter?",
                                                "Confirmation",
                                                JOptionPane.YES_NO_OPTION);

                                        if (confirmResult1 == JOptionPane.NO_OPTION) {
                                            JOptionPane.showMessageDialog(null,
                                                    "The  serie was added successfully!");
                                            break;
                                        }

                                        JPanel panel3 = new JPanel(new GridLayout(4, 4, 2, 2));
                                        JTextField nameChapterField2 = new JTextField();
                                        JTextField descriptionChapterField2 = new JTextField();
                                        JTextField durationChapterField2 = new JTextField();

                                        panel3.add(new JLabel("Name of the chapter:"));
                                        panel3.add(nameChapterField2);
                                        panel3.add(new JLabel("Description of the chapter:"));
                                        panel3.add(descriptionChapterField2);
                                        panel3.add(new JLabel("Duration of the chapter:"));
                                        panel3.add(durationChapterField2);
                                        while (true) {

                                            int result3 = JOptionPane.showConfirmDialog(
                                                    null,
                                                    panel3,
                                                    "Create Chapter Menu",
                                                    JOptionPane.OK_CANCEL_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE);

                                            if (result3 == JOptionPane.OK_OPTION) {
                                                String nameChapter2 = nameChapterField2.getText().trim();
                                                String descriptionChapter2 = descriptionChapterField2.getText().trim();
                                                String durationChapter3 = durationChapterField2.getText().trim();

                                                if (!nameChapter2.isBlank() && !descriptionChapter2.isBlank()
                                                        && !durationChapter3.isBlank()
                                                        && ac.validateName(nameChapter2)
                                                        && ac.validarSinCharacterSpecial(nameChapter2)
                                                        && ac.validateDescription(descriptionChapter2)
                                                        && ac.validateNumbers(durationChapter3)) {
                                                    int durationChapter2 = Integer.parseInt(durationChapter3);
                                                    ac.addChapter(nameChapter2, descriptionChapter2, durationChapter2,
                                                            ac.getCurrentSerie().getId(), seasonName);
                                                    JOptionPane.showMessageDialog(null,
                                                            "The chapter was added successfully!");
                                                    break;
                                                } else if (nameChapter2.isBlank() && descriptionChapter2.isBlank()
                                                        && durationChapter3.isBlank()) {
                                                    JOptionPane.showMessageDialog(null, "You must enter all the data");
                                                } else if (!ac.validateName(nameChapter2)
                                                        || !ac.validarSinCharacterSpecial(nameChapter2)) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "invalid chapter name, must have at least three letters and cannot have special characters");
                                                } else if (!ac.validateDescription(descriptionChapter2)) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Invalid description must have a minimum of five letters");
                                                } else if (!ac.validateNumbers(durationChapter3)) {
                                                    JOptionPane.showMessageDialog(null, "Duration invalid");
                                                }
                                            } else {
                                                break;
                                            }

                                        }

                                    }
                                    sm.addSerie(ac.getCurrentSerie());

                                    return 10;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Data not saved");
                                }
                            } else if (nameChapter.isBlank() && descriptionChapter.isBlank()
                                    && String.valueOf(durationChapter1).isBlank() && seasonName.isBlank()) {
                                JOptionPane.showMessageDialog(null, "You must enter all the data");
                            } else if (!ac.validateName(nameChapter) || !ac.validarSinCharacterSpecial(nameChapter)) {
                                JOptionPane.showMessageDialog(null,
                                        "invalid chapter name must have at least three letters and cannot have special characters");
                            } else if (!ac.validateName(seasonName) || !ac.validarSinCharacterSpecial(seasonName)) {
                                JOptionPane.showMessageDialog(null,
                                        "invalid season name must have at least three letters and cannot have special characters");
                            } else if (!ac.validateDescription(descriptionChapter)) {
                                JOptionPane.showMessageDialog(null,
                                        "Invalid description must have a minimum of five letters");
                            } else if (!ac.validateNumbers(durationChapter1)) {
                                JOptionPane.showMessageDialog(null, "Duration invalid");
                            }
                        } else {
                            break;
                        }
                    }

                } else if (name.isBlank() && author.isBlank() && description.isBlank()) {
                    JOptionPane.showMessageDialog(null, "You must enter all the data");
                } else if (!ac.validateName(author)) {
                    JOptionPane.showMessageDialog(null, "invalid name of author must have at least three letters");
                } else if (!ac.validateDescription(description)) {
                    JOptionPane.showMessageDialog(null, "Invalid description must have a minimum of five letters");
                } else if (!ac.validateName(name) || !ac.validarSinCharacterSpecial(name)) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid serie name  must have at least three letters and cannot have special characters");
                } else if (!ac.containCharacterSpecial(author)) {
                    JOptionPane.showMessageDialog(null, "The author's name cannot have special characters");
                }

            } else {
                break;
            }
        }
        return 10;

    }

    // ----------------------------------------See Series
    // menu(12)------------------------------------------------------------------------//

    public int ShowSeriesMenu(int op) {
        if (ac.getListSeries().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no series created yet");
        } else {
            while (true) {
                String selectedSerieName = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose a serie",
                        "Serie",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        ac.ShowListSeriesNames().toArray(),
                        null);

                if (selectedSerieName != null) {
                    String[] names = selectedSerieName.split("-");

                    while (true) {
                        String selectedSeasonName = (String) JOptionPane.showInputDialog(
                                null,
                                ac.showSeries(Integer.parseInt(names[1])) + "\nChoose a season",
                                selectedSerieName,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                ac.ShowListSeasonNames(Integer.parseInt(names[1])).toArray(),
                                null);

                        if (selectedSeasonName != null) {
                            while (true) {
                                String selectedChaptername = (String) JOptionPane.showInputDialog(
                                        null,
                                        ac.showSeason(selectedSeasonName, Integer.parseInt(names[1]))
                                                + "\nChoose a chapter",
                                        selectedSerieName + " seasons",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        ac.showListChapterNames(Integer.parseInt(names[1]), selectedSeasonName)
                                                .toArray(),
                                        null);

                                if (selectedChaptername != null) {
                                    JOptionPane.showMessageDialog(null, ac.showChapters(selectedSeasonName,
                                            Integer.parseInt(names[1]), selectedChaptername));
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return 10;
    }

    // -----------------------------User Registered-------------------------------

    // Menu(21)

    public int userRegisteredMenu(int op) {
        Icon userMenu = new ImageIcon(System.getProperty("user.dir")
                + "\\MULTIMEDIA PROJECT\\src\\co\\edu\\uptc\\persistence\\files\\pics\\UserMenu.jpg");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Welcome to registered user"), BorderLayout.NORTH);
        panel.add(new JLabel(userMenu), BorderLayout.CENTER);
        op = JOptionPane.showOptionDialog(null, panel,
                "Registered Menu", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "Movies", "Series", "PlayList Management", "Categories", "Suscriptions",
                        "Return" },
                null);

        LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
        if (userRegisteredC.getCurrentUser().getSub() != null) {
            if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub().getEndTime()) {
                userRegisteredC.getCurrentUser().setSub(null);
            }
        }

        switch (op) {
            case 0:
                op = 21;
                break;
            case 1:
                op = 22;
                break;
            case 2:
                op = 30;
                break;
            case 3:
                op = 41;
                break;
            case 4:
                op = 40;
                break;
            case 5:
                op = 0;
                break;

        }
        return op;
    }

    // -----------------------------Movies-------------------------------

    // Menu(21)

    public int ShowMovies(int op) {
        String[] movieNames = userRegisteredC.getMovieNames();

        if (movieNames.length == 0) {
            JOptionPane.showMessageDialog(null, "There are not movies avaliable", "User Movies Menu",
                    JOptionPane.INFORMATION_MESSAGE);
            return 20;
        }

        while (true) {
            LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
            if (userRegisteredC.getCurrentUser().getSub() != null) {
                if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub()
                        .getEndTime()) {
                    userRegisteredC.getCurrentUser().setSub(null);
                }
            }
            String selectedMovieName = (String) JOptionPane.showInputDialog(null, "Choose a movie",
                    "User Movies Menu",
                    JOptionPane.PLAIN_MESSAGE, null, movieNames, movieNames[0]);
            if (selectedMovieName == null) {
                return 20;
            }

            String[] aux = selectedMovieName.split("-");
            op = JOptionPane.showOptionDialog(null,
                    "Movie name: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getName() +
                            "\nDuration: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration() +
                            "\nDescription: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDescription() +
                            "\nAuthor: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getAuthor(),
                    "See Movie From PlayList Menu", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, new Object[] { "Play Movie", "choose other movie", "Return" },
                    null);

            if (op == 0) {
                if (userRegisteredC.getCurrentUser().getSub() == null) {
                    int add = (int) (Math.random() * 5);
                    ImageIcon addPic = (ImageIcon) addsPics[add];
                    JFrame addFrame = new JFrame("Publicity Space");
                    addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (screenSize.width - addFrame.getWidth()) / 2;
                    int y = (screenSize.height - addFrame.getHeight()) / 2;
                    addFrame.setLocation(x, y);

                    JPanel addPanel = new JPanel(new BorderLayout());
                    JProgressBar progressBar = new JProgressBar(0, 100);
                    progressBar.setStringPainted(true);
                    addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                    addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                    addPanel.add(progressBar, BorderLayout.SOUTH);
                    addFrame.add(addPanel);

                    addFrame.setVisible(true);

                    for (int i = 0; i <= 100; i++) {
                        progressBar.setValue(i);
                        try {
                            Thread.sleep((long) (3000 * 0.01));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    addFrame.dispose();
                }
                long duration = userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration();

                JFrame frame = new JFrame("Playing Movie");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (screenSize.width - frame.getWidth()) / 2;
                int y = (screenSize.height - frame.getHeight()) / 2;
                frame.setLocation(x, y);

                JPanel panel = new JPanel(new BorderLayout());
                JProgressBar progressBar = new JProgressBar(0, 100);
                progressBar.setStringPainted(true);

                panel.add(progressBar, BorderLayout.CENTER);

                frame.add(panel);

                frame.setVisible(true);

                for (int i = 0; i <= 100; i++) {
                    progressBar.setValue(i);
                    try {
                        Thread.sleep((long) (duration * 0.01));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.dispose();

                op = JOptionPane.showOptionDialog(null, "Movie played!", null, JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[] { "Choose other movie", "Return" },
                        null);
                if (op == 1) {
                    return 20;
                }
            } else if (op == 2) {
                break;
            }
        }
        return 20;
    }

    // -----------------------------Series-------------------------------

    // Menu(22)
    public int ShowSeries(int op) {
        String[] serieNames = userRegisteredC.getSerieNames();
        if (serieNames.length == 0) {
            JOptionPane.showMessageDialog(null, "There are not series available", "User Series Menu",
                    JOptionPane.INFORMATION_MESSAGE);
            return 20;
        }

        while (true) {
            String selectedSerieName = (String) JOptionPane.showInputDialog(null, "Choose a Serie",
                    "User Series Menu",
                    JOptionPane.PLAIN_MESSAGE, null, serieNames, serieNames[0]);

            if (selectedSerieName == null) {
                break;
            }
            // SelectSerieName ---> to change for id
            String[] aux = selectedSerieName.split("-");

            op = JOptionPane.showOptionDialog(null,
                    "Serie name: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getName() +
                            "\nDescription: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getDescription() +
                            "\nAuthor: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getAuthor(),
                    "See Movie ", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, new Object[] { "See Seasons", "choose other serie", "Return" },
                    null);

            if (op == 0) {
                while (true) {
                    String seasonName = (String) JOptionPane.showInputDialog(null, "Choose a season",
                            "Season Menu", JOptionPane.PLAIN_MESSAGE,
                            null, userRegisteredC.serieSeasonsNames(Integer.parseInt(aux[1])), null);
                    if (seasonName == null) {
                        break;
                    }
                    while (true) {
                        LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
                        if (userRegisteredC.getCurrentUser().getSub() != null) {
                            if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub()
                                    .getEndTime()) {
                                userRegisteredC.getCurrentUser().setSub(null);
                            }
                        }
                        String chapterName = (String) JOptionPane.showInputDialog(null, "Chose a chapter",
                                "Chapter Menu", JOptionPane.PLAIN_MESSAGE,
                                null, userRegisteredC.serieChapterNames(Integer.parseInt(aux[1]), seasonName), null);
                        if (chapterName == null) {
                            break;
                        }
                        op = JOptionPane.showOptionDialog(null, "Chapter name: " + chapterName +
                                "\nDuration: "
                                + userRegisteredC.getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                        .getDuration()
                                + "\nDescription: "
                                + userRegisteredC.getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                        .getDescription(),
                                "See Movie ", JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                new Object[] { "Play Chapter", "choose other chapter", "choose other season",
                                        "choose other serie", "Return" },
                                null);

                        if (op == 0) {
                            if (userRegisteredC.getCurrentUser().getSub() == null) {
                                int add = (int) (Math.random() * 5);
                                ImageIcon addPic = (ImageIcon) addsPics[add];
                                JFrame addFrame = new JFrame("Publicity Space");
                                addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                int x = (screenSize.width - addFrame.getWidth()) / 2;
                                int y = (screenSize.height - addFrame.getHeight()) / 2;
                                addFrame.setLocation(x, y);

                                JPanel addPanel = new JPanel(new BorderLayout());
                                JProgressBar progressBar = new JProgressBar(0, 100);
                                progressBar.setStringPainted(true);
                                addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                                addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                                addPanel.add(progressBar, BorderLayout.SOUTH);
                                addFrame.add(addPanel);

                                addFrame.setVisible(true);

                                for (int i = 0; i <= 100; i++) {
                                    progressBar.setValue(i);
                                    try {
                                        Thread.sleep((long) (3000 * 0.01));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                addFrame.dispose();
                            }

                            JFrame frame = new JFrame("Playing Chap");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setSize(300, 150);
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            int x = (screenSize.width - frame.getWidth()) / 2;
                            int y = (screenSize.height - frame.getHeight()) / 2;
                            frame.setLocation(x, y);

                            JPanel panel = new JPanel(new BorderLayout());
                            JProgressBar progressBar = new JProgressBar(0, 100);
                            progressBar.setStringPainted(true);

                            panel.add(progressBar, BorderLayout.CENTER);
                            frame.add(panel);

                            frame.setVisible(true);
                            int duration = userRegisteredC
                                    .getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                    .getDuration();

                            for (int i = 0; i < 100; i++) {

                                progressBar.setValue(i);
                                try {
                                    Thread.sleep((int) (duration * 0.01));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            frame.dispose();
                            op = JOptionPane.showOptionDialog(null, "chapter played!", null,
                                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[] { "Play other chapter", "choose other serie", "Choose other season",
                                            "Return" },
                                    null);

                            if (op == 1) {
                                return 22;
                            } else if (op == 2) {
                                break;
                            } else if (op == 3) {
                                return 20;
                            }
                        } else if (op == 2) {
                            break;
                        } else if (op == 3) {
                            return 22;
                        } else if (op == 4) {
                            return 20;
                        }
                    }
                }
            } else if (op == 2) {
                break;
            }
        }
        return 20;
    }

    // ----------------------------------------Update Series
    // menu(13)------------------------------------------------------------------------//

    public int updateSeriesMenu(int op) {
        if (ac.getListSeries().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no series created yet");
        } else {
            String selectedSerieName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a serie",
                    "Serie",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ac.ShowListSeriesNames().toArray(),
                    null);

            if (selectedSerieName != null) {
                int op2 = 0;
                String[] names = selectedSerieName.split("-");

                op2 = JOptionPane.showOptionDialog(null, "Choose an option to do with a serie",
                        "Update serie " + selectedSerieName,
                        JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                        null, new Object[] { "Seasons", "Update Serie", "Return" },
                        null);

                switch (op2) {
                    case 0:
                        int op3 = 0;
                        // ----------------------------------------Season Management
                        // ------------------------------------------------------------------------//
                        while (op3 != 4) {
                            op3 = JOptionPane.showOptionDialog(null, "Choose an option to do with a season",
                                    "Season menu of the " + selectedSerieName + " serie",
                                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[] { "See Season", "Create Season", "Update Season", "Remove Season",
                                            "Return" },
                                    null);
                            switch (op3) {
                                case 0:
                                    String selectedSeasonName = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Choose a season",
                                            selectedSerieName + " seasons",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            ac.ShowListSeasonNames(Integer.parseInt(names[1])).toArray(),
                                            null);

                                    if (selectedSeasonName != null) {
                                        while (true) {
                                            String selectedChaptername = (String) JOptionPane.showInputDialog(
                                                    null,
                                                    ac.showSeason(selectedSeasonName, Integer.parseInt(names[1]))
                                                            + "\nChoose a chapter",
                                                    selectedSerieName + " seasons",
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    ac.showListChapterNames(Integer.parseInt(names[1]),
                                                            selectedSeasonName)
                                                            .toArray(),
                                                    null);

                                            if (selectedChaptername != null) {
                                                JOptionPane.showMessageDialog(null, ac.showChapters(selectedSeasonName,
                                                        Integer.parseInt(names[1]), selectedChaptername));
                                            } else {
                                                break;
                                            }
                                        }
                                    } else {
                                        break;
                                    }

                                    break;
                                case 1:
                                    // add another season

                                    JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

                                    JTextField seasonNameField = new JTextField();
                                    JTextField nameChapterField = new JTextField();
                                    JTextField descriptionChapterField = new JTextField();
                                    JTextField durationChapterField = new JTextField();

                                    panel.add(new JLabel("Season name:"));
                                    panel.add(seasonNameField);
                                    panel.add(new JLabel("Name of the chapter:"));
                                    panel.add(nameChapterField);
                                    panel.add(new JLabel("Description of the chapter:"));
                                    panel.add(descriptionChapterField);
                                    panel.add(new JLabel("Duration of the chapter:"));
                                    panel.add(durationChapterField);

                                    while (true) {
                                        int result = JOptionPane.showConfirmDialog(
                                                null,
                                                panel,
                                                "Create Season Menu",
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.PLAIN_MESSAGE);

                                        if (result == JOptionPane.OK_OPTION) {
                                            String seasonName = seasonNameField.getText().trim();
                                            String nameChapter = nameChapterField.getText().trim();
                                            String descriptionChapter = descriptionChapterField.getText().trim();
                                            String durationChapter1 = durationChapterField.getText().trim();

                                            if (!seasonName.isBlank()&& seasonName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$") 
                                                    && nameChapter.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")
                                                    && !nameChapter.isBlank()
                                                    && !descriptionChapter.isBlank() && !durationChapter1.isBlank()
                                                    && ac.validateNumbers(durationChapter1)
                                                    && ac.validateName(nameChapter)
                                                    && ac.validateDescription(descriptionChapter)
                                                    && ac.validateNameSeason(seasonName, Integer.parseInt(names[1]))) {
                                                // Agregar el primer capítulo a la temporada
                                                int durationChapter = Integer.parseInt(durationChapter1);
                                                ac.addSeason(Integer.parseInt(names[1]), seasonName,
                                                        ac.createChapter(nameChapter,
                                                                descriptionChapter, durationChapter));
                                                JOptionPane.showMessageDialog(null, "Season was created correctly");
                                                while (true) {

                                                    // Ahora agregar el segundo capítulo a la misma temporada
                                                    int confirmResult = JOptionPane.showConfirmDialog(
                                                            null,
                                                            "Do you want to add another chapter?",
                                                            "Confirmation",
                                                            JOptionPane.YES_NO_OPTION);

                                                    if (confirmResult == JOptionPane.YES_OPTION) {
                                                        JPanel panel2 = new JPanel(new GridLayout(4, 4, 2, 2));
                                                        JTextField nameChapterField2 = new JTextField();
                                                        JTextField descriptionChapterField2 = new JTextField();
                                                        JTextField durationChapterField2 = new JTextField();

                                                        panel2.add(new JLabel("Name of the chapter:"));
                                                        panel2.add(nameChapterField2);
                                                        panel2.add(new JLabel("Description of the chapter:"));
                                                        panel2.add(descriptionChapterField2);
                                                        panel2.add(new JLabel("Duration of the chapter:"));
                                                        panel2.add(durationChapterField2);

                                                        while (true) {
                                                            int result2 = JOptionPane.showConfirmDialog(
                                                                    null,
                                                                    panel2,
                                                                    "Create chapter",
                                                                    JOptionPane.OK_CANCEL_OPTION,
                                                                    JOptionPane.PLAIN_MESSAGE);

                                                            if (result2 == JOptionPane.OK_OPTION) {
                                                                String nameChapter2 = nameChapterField2.getText();
                                                                String descriptionChapter2 = descriptionChapterField2
                                                                        .getText();
                                                                String durationChapter3 = durationChapterField2
                                                                        .getText();

                                                                if (!nameChapter2.isBlank()
                                                                        && nameChapter2.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")
                                                                        && !descriptionChapter2.isBlank()
                                                                        && !durationChapter3.isBlank()
                                                                        && ac.validateNumbers(durationChapter3)
                                                                        && ac.validateName(nameChapter2)
                                                                        && ac.validateDescription(
                                                                                descriptionChapter2)) {
                                                                    int durationChapter2 = Integer
                                                                            .parseInt(durationChapter3);
                                                                    ac.addChapter(nameChapter2, descriptionChapter2,
                                                                            durationChapter2,
                                                                            Integer.parseInt(names[1]), seasonName);
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "Chapter was created correctly");
                                                                    break;

                                                                } else if (nameChapter2.isBlank()
                                                                        && descriptionChapter2.isBlank()
                                                                        && durationChapter3.isBlank()) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "You must enter all the data");
                                                                } else if (!ac.validateNumbers(durationChapter3)) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "Duration invalid");
                                                                } else if (!ac.validateName(nameChapter2)) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "invalid name of chapter must have at least three letters");
                                                                } else if (!ac
                                                                        .validateDescription(descriptionChapter2)) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "Invalid description must have a minimum of five letters");
                                                                }
                                                            } else {
                                                                break;
                                                            }

                                                        }

                                                    } else {
                                                        break;
                                                    }

                                                }
                                                break;

                                            } else if (seasonName.isBlank() && nameChapter.isBlank()
                                                    && descriptionChapter.isBlank() && durationChapter1.isBlank()) {
                                                JOptionPane.showMessageDialog(null, "You must enter all the data");
                                            } else if (!ac.validateNumbers(durationChapter1)) {
                                                JOptionPane.showMessageDialog(null, "Duration invalid");
                                            } else if (!ac.validateName(nameChapter)) {
                                                JOptionPane.showMessageDialog(null,
                                                        "invalid name of chapter must have at least three letters");
                                            } else if (!ac.validateDescription(descriptionChapter)) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Invalid description must have a minimum of five letters");
                                            } else if (!ac.validateNameSeason(seasonName, Integer.parseInt(names[1]))) {
                                                JOptionPane.showMessageDialog(null,
                                                        "invalid name of season, This name already exists ");
                                            }

                                        } else {
                                            break;
                                        }

                                    }

                                    break;
                                case 2:
                                    // Update the season

                                    selectedSeasonName = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Choose a season",
                                            selectedSerieName + " seasons",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            ac.ShowListSeasonNames(Integer.parseInt(names[1])).toArray(),
                                            null);

                                    if (selectedSeasonName != null) {
                                        int opUpdateSeason = 0;

                                        opUpdateSeason = JOptionPane.showOptionDialog(null,
                                                "Choose an option to do with a season", "Update season",
                                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                                null, new Object[] { "Chapters", "Update Season", "Return" },
                                                null);

                                        switch (opUpdateSeason) {
                                            case 0:
                                                int opChapters = 0;
                                                String selectedChaptername = "";
                                                // ----------------------------------------Chapters Management
                                                // ------------------------------------------------------------------------//
                                                while (opChapters != 4) {
                                                    opChapters = JOptionPane.showOptionDialog(null,
                                                            "Choose an option to do with a season",
                                                            "Admin Menu",
                                                            JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                                            null,
                                                            new Object[] { "See chapters", "Create chapters",
                                                                    "Update chapters", "Remove chapters",
                                                                    "Return" },
                                                            null);
                                                    switch (opChapters) {
                                                        case 0:
                                                            selectedChaptername = (String) JOptionPane.showInputDialog(
                                                                    null,
                                                                    "Choose a chapter",
                                                                    selectedSerieName + " seasons",
                                                                    JOptionPane.PLAIN_MESSAGE,
                                                                    null,
                                                                    ac.showListChapterNames(Integer.parseInt(names[1]),
                                                                            selectedSeasonName).toArray(),
                                                                    null);

                                                            if (selectedChaptername != null) {
                                                                JOptionPane.showMessageDialog(null, ac.showChapters(
                                                                        selectedSeasonName, Integer.parseInt(names[1]),
                                                                        selectedChaptername));

                                                            }

                                                            break;
                                                        case 1:
                                                            JPanel panel6 = new JPanel(new GridLayout(4, 4, 2, 2));
                                                            JTextField nameChapterField6 = new JTextField();
                                                            JTextField descriptionChapterField6 = new JTextField();
                                                            JTextField durationChapterField6 = new JTextField();

                                                            panel6.add(new JLabel("Name of the chapter:"));
                                                            panel6.add(nameChapterField6);
                                                            panel6.add(new JLabel("Description of the chapter:"));
                                                            panel6.add(descriptionChapterField6);
                                                            panel6.add(new JLabel("Duration of the chapter:"));
                                                            panel6.add(durationChapterField6);

                                                            while (true) {

                                                                int result3 = JOptionPane.showConfirmDialog(
                                                                        null,
                                                                        panel6,
                                                                        "Create Chapter Menu",
                                                                        JOptionPane.OK_CANCEL_OPTION,
                                                                        JOptionPane.PLAIN_MESSAGE);

                                                                if (result3 == JOptionPane.OK_OPTION) {
                                                                    String nameChapter2 = nameChapterField6.getText();
                                                                    String descriptionChapter2 = descriptionChapterField6
                                                                            .getText();
                                                                    String durationChapter3 = durationChapterField6
                                                                            .getText();

                                                                    if (!nameChapter2.isBlank()
                                                                            && !descriptionChapter2.isBlank()
                                                                            && !durationChapter3.isBlank()
                                                                            && ac.validateName(nameChapter2)
                                                                            && ac.validateDescription(
                                                                                    descriptionChapter2)
                                                                            && ac.validateNumbers(durationChapter3)
                                                                            && nameChapter2.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")
                                                                            && ac.validateNameChapter(
                                                                                    selectedSeasonName,
                                                                                    Integer.parseInt(names[1]),
                                                                                    nameChapter2)) {
                                                                        int durationChapter2 = Integer
                                                                                .parseInt(durationChapter3);
                                                                        ac.addChapter(nameChapter2, descriptionChapter2,
                                                                                durationChapter2,
                                                                                Integer.parseInt(names[1]),
                                                                                selectedSeasonName);
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "Chapter was created correctly");
                                                                        int confirmResult1 = JOptionPane
                                                                                .showConfirmDialog(
                                                                                        null,
                                                                                        "Do you want to add another chapter?",
                                                                                        "Confirmation",
                                                                                        JOptionPane.YES_NO_OPTION);

                                                                        if (confirmResult1 == JOptionPane.NO_OPTION) {
                                                                            break;
                                                                        }

                                                                    } else if (nameChapter2.isBlank()
                                                                            && descriptionChapter2.isBlank()
                                                                            && durationChapter3.isBlank()) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "You must enter all the data");
                                                                    } else if (!ac.validateName(nameChapter2)) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "invalid name of chapter must have at least three letters");
                                                                    } else if (!ac
                                                                            .validateDescription(descriptionChapter2)) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "Invalid description must have a minimum of five letters");
                                                                    } else if (!ac.validateNumbers(durationChapter3)) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "Duration invalid");
                                                                    } else if (!ac.validateNameChapter(
                                                                            selectedSeasonName,
                                                                            Integer.parseInt(names[1]), nameChapter2)) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "invalid name of chapter, the name already exists");
                                                                    }

                                                                } else {
                                                                    break;
                                                                }
                                                            }

                                                            break;
                                                        case 2:
                                                            // Actualiza capitulos
                                                            selectedChaptername = (String) JOptionPane
                                                                    .showInputDialog(
                                                                            null,
                                                                            "Choose a chapter",
                                                                            selectedSerieName + " seasons",
                                                                            JOptionPane.PLAIN_MESSAGE,
                                                                            null,
                                                                            ac.showListChapterNames(
                                                                                    Integer.parseInt(names[1]),
                                                                                    selectedSeasonName).toArray(),
                                                                            null);

                                                            if (selectedChaptername != null) {
                                                                JPanel panel3 = new JPanel(new GridLayout(5, 5, 2, 2));

                                                                MultimediaContent selectedChapter = ac.getListSeries()
                                                                        .get(ac.serieFound(Integer.parseInt(names[1])))
                                                                        .getSeasons()
                                                                        .get(ac.seasonFound(selectedSeasonName,
                                                                                Integer.parseInt(names[1])))
                                                                        .getSeasonMultimediaContent()
                                                                        .get(ac.chapterFound(selectedSeasonName,
                                                                                Integer.parseInt(names[1]),
                                                                                selectedChaptername));

                                                                JTextField nameChapterField2 = new JTextField(
                                                                        selectedChapter.getName());
                                                                JTextField descriptionChapterField2 = new JTextField(
                                                                        selectedChapter.getDescription());
                                                                JTextField durationChapterField2 = new JTextField(
                                                                        Integer.toString(
                                                                                selectedChapter.getDuration()));
                                                                String oldName = selectedChapter.getName();

                                                                panel3.add(new JLabel("Name of the chapter:"));
                                                                panel3.add(nameChapterField2);
                                                                panel3.add(new JLabel("Description of the chapter:"));
                                                                panel3.add(descriptionChapterField2);
                                                                panel3.add(new JLabel("Duration of the chapter:"));
                                                                panel3.add(durationChapterField2);

                                                                // ...

                                                                while (true) {
                                                                    int result2 = JOptionPane.showConfirmDialog(
                                                                            null,
                                                                            panel3,
                                                                            "Update series Menu",
                                                                            JOptionPane.OK_CANCEL_OPTION,
                                                                            JOptionPane.PLAIN_MESSAGE);

                                                                    if (result2 == JOptionPane.OK_OPTION) {
                                                                        // Obtener los nuevos valores de los campos de
                                                                        // texto
                                                                        String newName = nameChapterField2.getText()
                                                                                .trim();
                                                                        String durationChapter3 = durationChapterField2
                                                                                .getText().trim();
                                                                        String newDescription = descriptionChapterField2
                                                                                .getText().trim();
                                                                        oldName = selectedChapter.getName();

                                                                        if (!newName.isBlank()
                                                                                && !newDescription.isBlank()
                                                                                && !durationChapter3.isBlank()
                                                                                && ac.validateName(newName)
                                                                                && ac.validateDescription(
                                                                                        newDescription)
                                                                                && ac.validateNumbers(durationChapter3)
                                                                                && newName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")
                                                                                && (oldName.equals(newName)
                                                                                        || ac.validateNameChapter(
                                                                                                selectedSeasonName,
                                                                                                Integer.parseInt(
                                                                                                        names[1]),
                                                                                                newName))) {

                                                                            int durationChapter2 = Integer
                                                                                    .parseInt(durationChapter3);
                                                                            int confirmResult = JOptionPane
                                                                                    .showConfirmDialog(
                                                                                            null,
                                                                                            "Are you sure about these changes?"
                                                                                                    + "\nName chapter: "
                                                                                                    + newName
                                                                                                    + "\nDuration of chapter: "
                                                                                                    + durationChapter2
                                                                                                    + "\nDescription of chapter: "
                                                                                                    + newDescription,
                                                                                            "Confirmation",
                                                                                            JOptionPane.YES_NO_OPTION);

                                                                            if (confirmResult == JOptionPane.YES_OPTION) {
                                                                                // Llamar al método para modificar el
                                                                                // capítulo
                                                                                ac.modifyChapters(newDescription,
                                                                                        newName, durationChapter2,
                                                                                        Integer.parseInt(names[1]),
                                                                                        selectedSeasonName,
                                                                                        selectedChaptername);

                                                                                JOptionPane.showMessageDialog(null,
                                                                                        "The chapter was modified successfully!");
                                                                                break;
                                                                            }
                                                                        } else if (newName.isBlank()
                                                                                && newDescription.isBlank()
                                                                                && durationChapter3.isBlank()) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "You must enter all the data");
                                                                        } else if (!ac.validateName(newName)) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "invalid name of chapter must have at least three letters");
                                                                        } else if (!ac
                                                                                .validateDescription(newDescription)) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "Invalid description must have a minimum of five letters");
                                                                        } else if (!ac
                                                                                .validateNumbers(durationChapter3)) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "Duration invalid");
                                                                        } else if (!oldName.equals(newName)
                                                                                && !ac.validateNameChapter(
                                                                                        selectedSeasonName,
                                                                                        Integer.parseInt(names[1]),
                                                                                        newName)) {
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "invalid name of chapter, the name already exists");
                                                                        }
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }
                                                            }

                                                            break;
                                                        case 3:
                                                            selectedChaptername = (String) JOptionPane.showInputDialog(
                                                                    null,
                                                                    "Choose a chapter",
                                                                    selectedSerieName + " seasons",
                                                                    JOptionPane.PLAIN_MESSAGE,
                                                                    null,
                                                                    ac.showListChapterNames(Integer.parseInt(names[1]),
                                                                            selectedSeasonName).toArray(),
                                                                    null);

                                                            if (selectedChaptername != null) {
                                                                int confirmResult = 0;
                                                                if (ac.deletefirstchapter(selectedSeasonName,
                                                                        Integer.parseInt(names[1]))) {

                                                                    confirmResult = JOptionPane.showConfirmDialog(
                                                                            null,
                                                                            "Are you sure to the delete a chapter "
                                                                                    + selectedChaptername
                                                                                    + " ?,The season will also be eliminated",
                                                                            "Confirmation",
                                                                            JOptionPane.YES_NO_OPTION);
                                                                    if (confirmResult == JOptionPane.YES_OPTION) {
                                                                        ac.deleteChapters(selectedSeasonName,
                                                                                Integer.parseInt(names[1]),
                                                                                selectedChaptername);

                                                                        JOptionPane.showMessageDialog(null,
                                                                                "The chapter was deleted successfully!");

                                                                        if (ac.deletefirst(
                                                                                Integer.parseInt(names[1]))) {

                                                                            ac.deleteSerie(Integer.parseInt(names[1]));
                                                                            JOptionPane.showMessageDialog(null,
                                                                                    "The serie was deleted successfully!");

                                                                            return 10;
                                                                        }
                                                                        ac.deleteSeason(selectedSeasonName,
                                                                                Integer.parseInt(names[1]));
                                                                        sm.updateSerie(ac.getListSeries());
                                                                        JOptionPane.showMessageDialog(null,
                                                                                "The season was deleted successfully!");

                                                                        return 10;

                                                                    } else {
                                                                        break;
                                                                    }

                                                                } else {
                                                                    confirmResult = JOptionPane.showConfirmDialog(
                                                                            null,
                                                                            "Are you sure to the delete a chapter "
                                                                                    + selectedChaptername + " ?",
                                                                            "Confirmation",
                                                                            JOptionPane.YES_NO_OPTION);
                                                                    if (confirmResult == JOptionPane.YES_OPTION) {
                                                                        ac.deleteChapters(selectedSeasonName,
                                                                                Integer.parseInt(names[1]),
                                                                                selectedChaptername);

                                                                        JOptionPane.showMessageDialog(null,
                                                                                "The chapter was deleted successfully!");
                                                                        break;
                                                                    } else {
                                                                        break;
                                                                    }
                                                                }

                                                            }

                                                            break;
                                                        case 4:

                                                            break;

                                                    }
                                                }

                                                break;
                                            case 1:
                                                int seriesId = Integer.parseInt(names[1]);
                                                int serieIndex = ac.serieFound(seriesId);
                                                int seasonIndex = ac.seasonFound(selectedSeasonName, seriesId);

                                                if (serieIndex != -1 && seasonIndex != -1) {
                                                    JPanel panel4 = new JPanel(new GridLayout(1, 1, 2, 2));
                                                    JTextField seasonNameFieldNew = new JTextField(ac.getListSeries()
                                                            .get(serieIndex)
                                                            .getSeasons()
                                                            .get(seasonIndex)
                                                            .getSeasonName());

                                                    panel4.add(new JLabel("Name of the season:"));
                                                    panel4.add(seasonNameFieldNew);

                                                    while (true) {
                                                        int result2 = JOptionPane.showConfirmDialog(
                                                                null,
                                                                panel4,
                                                                "Update series Menu",
                                                                JOptionPane.OK_CANCEL_OPTION,
                                                                JOptionPane.PLAIN_MESSAGE);

                                                        if (result2 == JOptionPane.OK_OPTION) {
                                                            String newSeasonName = seasonNameFieldNew.getText().trim();
                                                            String oldSeasonName = ac.getListSeries()
                                                                    .get(serieIndex)
                                                                    .getSeasons()
                                                                    .get(seasonIndex)
                                                                    .getSeasonName();

                                                            if (!newSeasonName.isBlank()
                                                                    && (newSeasonName.equals(oldSeasonName)
                                                                            || ac.validateNameSeason(newSeasonName,
                                                                                    seriesId))
                                                                    && ac.validateName(newSeasonName) && newSeasonName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")) {
                                                                if (ac.modifySeason(newSeasonName, seriesId,
                                                                        selectedSeasonName)) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "The season was modified successfully!");
                                                                    break;
                                                                }
                                                            } else if (newSeasonName.isBlank()) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "Must be enter a name for the season");
                                                            } else if (!newSeasonName.equals(oldSeasonName) && !ac
                                                                    .validateNameSeason(newSeasonName, seriesId)) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "invalid season name, This name already exists");
                                                            } else if (!ac.validateName(newSeasonName)) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "invalid the new name must be at least three letters");
                                                            }
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                }
                                        }

                                    }
                                    break;
                                case 3:
                                    selectedSeasonName = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Choose a season",
                                            selectedSerieName + " seasons",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            ac.ShowListSeasonNames(Integer.parseInt(names[1])).toArray(),
                                            null);

                                    if (selectedSeasonName != null) {
                                        int confirmResult = JOptionPane.showConfirmDialog(
                                                null,
                                                "Are you sure to the delete a season " + selectedSeasonName + " ?",
                                                "Confirmation",
                                                JOptionPane.YES_NO_OPTION);

                                        if (confirmResult == JOptionPane.YES_OPTION) {

                                            if (ac.deletefirst(Integer.parseInt(names[1]))) {
                                                int confirmResult1 = JOptionPane.showConfirmDialog(
                                                        null,
                                                        "Are you sure to the delete a season," + selectedSeasonName
                                                                + "?" + " The series is also eliminated",
                                                        "Confirmation",
                                                        JOptionPane.YES_NO_OPTION);
                                                if (confirmResult1 == JOptionPane.YES_OPTION) {
                                                    ac.deleteSeason(selectedSeasonName, Integer.parseInt(names[1]));
                                                    JOptionPane.showMessageDialog(null,
                                                            "The season was deleted successfully!");

                                                    ac.deleteSerie(Integer.parseInt(names[1]));
                                                    JOptionPane.showMessageDialog(null,
                                                            "The serie was deleted successfully!");
                                                    return 10;

                                                } else {
                                                    JOptionPane.showMessageDialog(null, "was successfully canceled!");
                                                }

                                            } else {
                                                ac.deleteSeason(selectedSeasonName, Integer.parseInt(names[1]));
                                                JOptionPane.showMessageDialog(null,
                                                        "The season was deleted successfully!");
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "was successfully canceled!");
                                            break;
                                        }
                                    }
                                    break;
                            }
                            sm.updateSerie(ac.getListSeries());

                        }

                        break;
                    case 1:

                        int selectedSerie = ac.serieFound(Integer.parseInt(names[1]));

                        JPanel panel2 = new JPanel(new GridLayout(3, 3, 2, 2));

                        JTextField nameField = new JTextField(ac.getListSeries().get(selectedSerie).getName());
                        JTextField authorField = new JTextField(ac.getListSeries().get(selectedSerie).getAuthor());
                        JTextField descriptionField = new JTextField(
                                ac.getListSeries().get(selectedSerie).getDescription());

                        panel2.add(new JLabel("Name of the Serie:"));
                        panel2.add(nameField);
                        panel2.add(new JLabel("Author:"));
                        panel2.add(authorField);
                        panel2.add(new JLabel("Description:"));
                        panel2.add(descriptionField);

                        while (true) {
                            int result2 = JOptionPane.showConfirmDialog(
                                    null,
                                    panel2,
                                    "Update series Menu",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE);

                            if (result2 == JOptionPane.OK_OPTION) {
                                // Obtener los nuevos valores de los campos de texto
                                String newName = nameField.getText().trim();
                                String newAuthor = authorField.getText().trim();
                                String newDescription = descriptionField.getText().trim();

                                if (!newName.isBlank() && !newAuthor.isBlank() && !newDescription.isBlank()
                                        && ac.validateDescription(newDescription) && ac.validateName(newName)
                                        && ac.containCharacterSpecial(newAuthor) && ac.validateName(newAuthor)
                                        && newName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")) {
                                    int confirmResult = JOptionPane.showConfirmDialog(
                                            null,
                                            "Are you sure about these changes?" + "\nName serie: " + newName
                                                    + "\nAuthor of serie: " + newAuthor + "\nDescription of serie: "
                                                    + newDescription,
                                            "Confirmation",
                                            JOptionPane.YES_NO_OPTION);

                                    if (confirmResult == JOptionPane.YES_OPTION) {

                                        // The "Duration" para|meter in this method is 0 because we consider that series
                                        // dont have a duration excatly. Check it out
                                        ac.modifySeries(newDescription, newName, newAuthor, Integer.parseInt(names[1]));
                                        sm.updateSerie(ac.getListSeries());

                                        JOptionPane.showMessageDialog(null, "The serie was modified successfully!");
                                        return 10;
                                    }
                                } else if (newName.isBlank() && newAuthor.isBlank() && newDescription.isBlank()) {
                                    JOptionPane.showMessageDialog(null, "You must enter all the data");
                                } else if (!ac.validateName(newName)) {
                                    JOptionPane.showMessageDialog(null,
                                            "invalid the new name must be at least three letters");
                                } else if (!ac.validateDescription(newDescription)) {
                                    JOptionPane.showMessageDialog(null,
                                            "Invalid the new description must have a minimum of five letters");
                                } else if (!ac.validateName(newAuthor)) {
                                    JOptionPane.showMessageDialog(null,
                                            "invalid name of author must have at least three letters");
                                } else if (!ac.containCharacterSpecial(newAuthor)) {
                                    JOptionPane.showMessageDialog(null,
                                            "The author's name cannot have special characters");
                                }
                            } else {
                                break;
                            }

                        }

                        break;
                    default:
                        // Manejar casos no esperados si es necesario
                        break;
                }
            }
        }
        return 10;

    }

    // ----------------------------------------delete Series
    // menu(14)------------------------------------------------------------------------//

    public int deleteSeriesMenu(int op) {
        if (ac.getListSeries().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no series created yet");
        } else {
            String selectedSerieName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a serie",
                    "Delete serie",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    ac.ShowListSeriesNames().toArray(),
                    null);

            if (selectedSerieName != null) {
                String[] aux = selectedSerieName.split("-");
                int confirmResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to the delete a serie " + selectedSerieName + " ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    ac.deleteSerie(Integer.parseInt(aux[1]));
                    for (UserRegistered user : userRegisteredC.getAllUsers()) {
                        for (PlayList playL : user.getplayList()) {
                            for (Serie m : playL.getSeries()) {
                                if (m.getId() == Integer.parseInt(aux[1])) {
                                    playL.removeSerie(m);
                                    break;
                                }
                            }
                        }
                    }

                    for (Category c : categoryC.getCategories()) {
                        for (Serie s : c.getSeries()) {
                            if (s.getId() == Integer.parseInt(aux[1])) {
                                c.removeSerie(s);
                                break;
                            }
                        }
                    }
                    um.updateUsers(userRegisteredC.getAllUsers());
                    cm.updateCategories(categoryC.getCategories());
                    JOptionPane.showMessageDialog(null, "The serie was deleted successfully!");
                } else {

                }
            }
        }
        return 10;

    }

    // ----------------------------------------------Subscription Management Menu
    // (25)--------------------------------------------------//
    public int subscriptionManagerMenu(int op) {
        op = JOptionPane.showOptionDialog(null, "Welcome to Subscription Management Menu",
                "Subscription Management Menu",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
                new Object[] { "See", "Create", "Update", "Remove", "Return" }, null);

        switch (op) {
            case 0:op = 26;break;
            case 1:op = 27;break;
            case 2:op = 28;break;
            case 3:op = 29;break;
            case 4:op = 4;break;
        }

        return op;
    }

    // ----------------------------------------------See Subscriptions Menu
    // (26)--------------------------------------------------//
    public int seeSubscriptionsMenu() {
        if (subsC.getSubscriptions().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not subscriptions to see");
        } else {
            String subChoosen = (String) JOptionPane.showInputDialog(null, "Choose a subscription to see the content",
                    "See Subscriptions Menu",
                    JOptionPane.PLAIN_MESSAGE, null, subsC.subsNames(), null);
            if (subChoosen == null) {
                return 25;
            }

            subsC.findCurrentSubscription(subChoosen);
            JOptionPane.showMessageDialog(null, "Name: " + subChoosen +
                    "\nDescription: " + subsC.getCurrentSub().getDescription() +
                    "\nDuration: " + subsC.getCurrentSub().getDuration() +
                    "\nPrice: " + subsC.getCurrentSub().getPrice());
        }
        return 25;
    }

    // ----------------------------------------------Create Subscriptions Menu
    // (27)--------------------------------------------------//
    public int createSubscriptionsMenu(int op) {
        boolean aux = false;
        JPanel panel = new JPanel();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField durationField = new JTextField();
        while (true) {
            panel = new JPanel(new GridLayout(4, 4, 2, 2));

            panel.add(new JLabel("Name of the subscription:"));
            panel.add(nameField);
            panel.add(new JLabel("price:"));
            panel.add(priceField);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Duration:"));
            panel.add(durationField);

            op = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Create Subscription Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (!(op == JOptionPane.OK_OPTION)) {
                return 25;
            }
            if (!nameField.getText().isEmpty() && nameField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                if (!descriptionField.getText().isEmpty() && !descriptionField.getText().isBlank() && descriptionField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                    try {
                        Double.parseDouble(priceField.getText());
                        if (durationField.getText().matches("\\d+")) {
                            if (subsC.nameRepeated(nameField.getText())) {
                                JOptionPane.showMessageDialog(null, "There is a subscription with the same name");
                            }else{
                                aux = true;
                            } 
                        }else{
                            JOptionPane.showMessageDialog(null, "Duration not valid");
                        }   
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Price not valid");
                        return 27;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Description not valid");
                    return 27;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Name not valid");
                return 27;
            }
            while(aux){
                if (op == JOptionPane.OK_OPTION) {

                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    String description = descriptionField.getText();
                    int duration = Integer.parseInt(durationField.getText());
                    
                    int confirmResult = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure all the data you entered is correct?\n" +
                                    "Name of Subscription: " + name + "\nPrice: " + price + "\nDescription: "
                                    + description +
                                    "\nDuration: " + duration,
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        subsC.addSubscription(name, description, duration, price);

                        JOptionPane.showMessageDialog(null, "The subscription was added successfully!");
                        break;
                    } else {
                        aux = false;
                        break;
                    }
                        
                }else {
                    break;
                }
            }
            if(aux){break;}
            
        }
        return 25;
    }

    // ----------------------------------------------Update Subscriptions Menu
    // (28)--------------------------------------------------//
    public int updatesubscriptionMenu(int op) {
        boolean aux = false;
        if (subsC.getSubscriptions().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no subscriptions created yet");
        } else {
            String selectedSubscription = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a subscrition",
                    "Update Subscriptions Menu",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    subsC.subsNames(),
                    null);

            while(true){
                if (selectedSubscription != null) {
                    subsC.findCurrentSubscription(selectedSubscription);
                    Subscription subTOUpdate = new Subscription(subsC.getCurrentSub().getName(),
                            subsC.getCurrentSub().getDuration(),
                            subsC.getCurrentSub().getDescription(),
                            subsC.getCurrentSub().getPrice());

                    JPanel panel = new JPanel(new GridLayout(4, 4, 2, 2));

                    JTextField nameField = new JTextField(subsC.getCurrentSub().getName());
                    JTextField priceField = new JTextField(String.valueOf(subsC.getCurrentSub().getPrice()));
                    JTextField descriptionField = new JTextField(subsC.getCurrentSub().getDescription());
                    JTextField durationField = new JTextField(String.valueOf(subsC.getCurrentSub().getDuration()));

                    panel.add(new JLabel("Name of the subscription:"));
                    panel.add(nameField);
                    panel.add(new JLabel("price:"));
                    panel.add(priceField);
                    panel.add(new JLabel("Description:"));
                    panel.add(descriptionField);
                    panel.add(new JLabel("Duration:"));
                    panel.add(durationField);

                    int result = JOptionPane.showConfirmDialog(
                            null,
                            panel,
                            "Update Movies Menu",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        if (!nameField.getText().isEmpty() && nameField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                            if (!descriptionField.getText().isEmpty() && !descriptionField.getText().isBlank() && descriptionField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                                try {
                                    Double.parseDouble(priceField.getText());
                                    if (durationField.getText().matches("\\d+")) {
                                        if (subsC.nameRepeatedForUpdate(subsC.getCurrentSub().getName(), nameField.getText())) {
                                            JOptionPane.showMessageDialog(null, "There is a subscription with the same name");
                                        }else{
                                            aux = true;
                                        } 
                                    }else{
                                        JOptionPane.showMessageDialog(null, "Duration not valid");
                                    }   
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Price not valid");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Description not valid");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Name not valid");
                        }

                        while(aux){
                            String newName = nameField.getText();
                            double newPrice = Double.parseDouble(priceField.getText());
                            String newDescription = descriptionField.getText();
                            int newDuration = Integer.parseInt(durationField.getText());

                            int confirmResult = JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure about these changes?\n" +
                                            "Name of Subscription: " + newName + "\nprice: " + newPrice
                                            + "\nDescription: "
                                            + newDescription +
                                            "\nDuration: " + newDuration,
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION);

                            if (confirmResult == JOptionPane.YES_OPTION) {
                                subsC.modifySubscription(subTOUpdate, newName, newDescription, newDuration, newPrice);

                                JOptionPane.showMessageDialog(null, "The subscription was modified successfully!");
                                break;

                            }else{
                                aux = false;
                                break;
                            }
                        }
                    }else{
                        break;
                    }
                    if(aux){break;}
                }else{
                    break;
                }
            }
            
        }
        return 25;
    }

    // ----------------------------------------------Remove Subscriptions Menu
    // (29)--------------------------------------------------//
    public int removeSubscriptionMenu(int op) {
        if (subsC.getSubscriptions().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no subscriptions created yet");
        } else {
            String selectedSubscription = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose a movie",
                    "Movies",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    subsC.subsNames(),
                    null);

            if (selectedSubscription != null) {
                int confirmResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to the delete a movie " + selectedSubscription + " ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    subsC.findCurrentSubscription(selectedSubscription);
                    subsC.removeSubscription();
                    for (UserRegistered u : userRegisteredC.getAllUsers()) {
                        try {
                            if (u.getSub().getName().equals(selectedSubscription)) {
                                u.setSub(null);
                            }
                        } catch (Exception e) {
                        }

                    }
                    um.updateUsers(userRegisteredC.getAllUsers());
                    JOptionPane.showMessageDialog(null, "The subscription was deleted successfully!");
                } else {

                }
            }

        }

        return 25;
    }

    // ----------------------------PlayList
    // Menu(30)-------------------------------//
    public int playListMenu(int op) {

        op = JOptionPane.showOptionDialog(null, "Welcome to your playlist", "PlayList Menu", JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new Object[] { "Your PlayList", "Create PlayList", "Update PlayList", "Remove PlayList", "Return" },
                null);

        LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
        if (userRegisteredC.getCurrentUser().getSub() != null) {
            if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub().getEndTime()) {
                userRegisteredC.getCurrentUser().setSub(null);
            }
        }
        switch (op) {
            case 0:
                op = 31;
                break;
            case 1:
                op = 32;
                break;
            case 2:
                op = 33;
                break;
            case 3:
                op = 34;
                break;
            case 4:
                op = 20;
                break;
        }

        return op;
    }

    // ----------------------------PlayList
    // Menu(31)-------------------------------//
    public int seePlayListMenu(int op) {
        if (userRegisteredC.getCurrentUser().getplayList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not PlayList created yet");
            return 30;
        }

        while (true) {

            String playListName = (String) JOptionPane.showInputDialog(null, "Choose a PlayList", "Play List Menu",
                    JOptionPane.PLAIN_MESSAGE,
                    null, playListC.playListNames(), null);
            if (playListName == null) {
                return 30;
            }
            playListC.playListFound(playListName);

            op = JOptionPane.showOptionDialog(null, "PlayList: " + playListName, playListName + " Menu",
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, new Object[] { "Movies", "Series", "Choose other PlayList", "Return PlayList Menu" }, null);

            if (op == 0) {
                if (playListC.getCurrentPlayList().getMovies().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are not movies added yet");
                } else {
                    break;
                }
            } else if (op == 1) {
                if (playListC.getCurrentPlayList().getSeries().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are not series added yet");
                } else {
                    break;
                }
            }
            if (op == 3) {
                break;
            }

        }

        switch (op) {
            case 0:
                while (true) {
                    LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
                    if (userRegisteredC.getCurrentUser().getSub() != null) {
                        if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub()
                                .getEndTime()) {
                            userRegisteredC.getCurrentUser().setSub(null);
                        }
                    }
                    String movieName = (String) JOptionPane.showInputDialog(null, "Chose a Movie", "PLay List Menu",
                            JOptionPane.PLAIN_MESSAGE,
                            null, playListC.playListMovieNames(), null);
                    if (movieName == null) {
                        return 31;
                    }
                    String[] aux = movieName.split("-");
                    op = JOptionPane.showOptionDialog(null, "Movie name: " + movieName +
                            "\nDuration: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration() +
                            "\nDescription: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDescription() +
                            "\nAuthor: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getAuthor(),
                            "See Movie From PlayList Menu", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                            null, new Object[] { "Play Movie", "choose other movie", "choose other PlayList",
                                    "Return to PlayList Menu" },
                            null);
                    if (op == 0) {
                        if (userRegisteredC.getCurrentUser().getSub() == null) {
                            int add = (int) (Math.random() * 5);
                            ImageIcon addPic = (ImageIcon) addsPics[add];
                            JFrame addFrame = new JFrame("Publicity Space");
                            addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            int x = (screenSize.width - addFrame.getWidth()) / 2;
                            int y = (screenSize.height - addFrame.getHeight()) / 2;
                            addFrame.setLocation(x, y);

                            JPanel addPanel = new JPanel(new BorderLayout());
                            JProgressBar progressBar = new JProgressBar(0, 100);
                            progressBar.setStringPainted(true);
                            addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                            addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                            addPanel.add(progressBar, BorderLayout.SOUTH);
                            addFrame.add(addPanel);

                            addFrame.setVisible(true);

                            for (int i = 0; i <= 100; i++) {
                                progressBar.setValue(i);
                                try {
                                    Thread.sleep((long) (3000 * 0.01));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            addFrame.dispose();
                        }
                        long duration = userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration();

                        JFrame frame = new JFrame("Playing Movie");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(300, 150);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int x = (screenSize.width - frame.getWidth()) / 2;
                        int y = (screenSize.height - frame.getHeight()) / 2;
                        frame.setLocation(x, y);

                        JPanel panel = new JPanel(new BorderLayout());
                        JProgressBar progressBar = new JProgressBar(0, 100);
                        progressBar.setStringPainted(true);

                        panel.add(progressBar, BorderLayout.CENTER);
                        frame.add(panel);

                        frame.setVisible(true);

                        for (int i = 0; i <= 100; i++) {
                            progressBar.setValue(i);
                            try {
                                Thread.sleep((long) (duration * 0.01));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        frame.dispose();

                        op = JOptionPane.showOptionDialog(null, "Movie played!", null, JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                new Object[] { "Play other movie", "Choose other PlayList", "Return to PlayList Menu" },
                                null);
                        if (op == 1) {
                            return 31;
                        } else if (op == 2) {
                            return 30;
                        }
                    } else if (op == 2) {
                        return 31;
                    } else if (op == 3) {
                        return 30;
                    }
                }

            case 1:
                while (true) {

                    String serieName = (String) JOptionPane.showInputDialog(null, "Chose a serie",
                            "Play List Serie Menu", JOptionPane.PLAIN_MESSAGE,
                            null, playListC.playListSerieNames(), null);
                    if (serieName == null) {
                        return 31;
                    }
                    String[] aux = serieName.split("-");

                    while (true) {

                        String seasonName = (String) JOptionPane.showInputDialog(null, "Chose a season",
                                "Play List Season Menu", JOptionPane.PLAIN_MESSAGE,
                                null, playListC.playListSeasonsNames(Integer.parseInt(aux[1])), null);
                        if (seasonName == null) {
                            break;
                        }
                        // chapter play
                        while (true) {
                            LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
                            if (userRegisteredC.getCurrentUser().getSub() != null) {
                                if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser()
                                        .getSub().getEndTime()) {
                                    userRegisteredC.getCurrentUser().setSub(null);
                                }
                            }
                            String chapterName = (String) JOptionPane.showInputDialog(null, "Chose a chapter",
                                    "Play List Chapter Menu", JOptionPane.PLAIN_MESSAGE,
                                    null, playListC.playListChapterNames(Integer.parseInt(aux[1]), seasonName), null);
                            if (chapterName == null) {
                                break;
                            }
                            op = JOptionPane.showOptionDialog(null, "Movie name: " + chapterName +
                                    "\nDuration: "
                                    + playListC.getPlayListChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                            .getDuration()
                                    +
                                    "\nDescription: "
                                    + playListC.getPlayListChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                            .getDescription(),
                                    "See Movie From PlayList Menu", JOptionPane.PLAIN_MESSAGE,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    new Object[] { "Play Chapter", "choose other chapter", "choose other season",
                                            "choose other serie", "choose other play list", "Return to PlayList Menu" },
                                    null);
                            if (op == 0) {
                                if (userRegisteredC.getCurrentUser().getSub() == null) {
                                    int add = (int) (Math.random() * 5);
                                    ImageIcon addPic = (ImageIcon) addsPics[add];
                                    JFrame addFrame = new JFrame("Publicity Space");
                                    addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                    int x = (screenSize.width - addFrame.getWidth()) / 2;
                                    int y = (screenSize.height - addFrame.getHeight()) / 2;
                                    addFrame.setLocation(x, y);

                                    JPanel addPanel = new JPanel(new BorderLayout());
                                    JProgressBar progressBar = new JProgressBar(0, 100);
                                    progressBar.setStringPainted(true);
                                    addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                                    addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                                    addPanel.add(progressBar, BorderLayout.SOUTH);
                                    addFrame.add(addPanel);

                                    addFrame.setVisible(true);

                                    for (int i = 0; i <= 100; i++) {
                                        progressBar.setValue(i);
                                        try {
                                            Thread.sleep((long) (3000 * 0.01));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    addFrame.dispose();
                                }
                                JFrame frame = new JFrame("Playing Serie");
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(300, 150);
                                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                int x = (screenSize.width - frame.getWidth()) / 2;
                                int y = (screenSize.height - frame.getHeight()) / 2;
                                frame.setLocation(x, y);

                                JPanel panel = new JPanel(new BorderLayout());
                                JProgressBar progressBar = new JProgressBar(0, 100);
                                progressBar.setStringPainted(true);

                                panel.add(progressBar, BorderLayout.CENTER);
                                frame.add(panel);

                                frame.setVisible(true);
                                int duration = playListC
                                        .getPlayListChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                        .getDuration();

                                for (int i = 0; i < 100; i++) {

                                    progressBar.setValue(i);
                                    try {
                                        Thread.sleep((int) (duration * 0.01));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                frame.dispose();
                                op = JOptionPane.showOptionDialog(null, "chapter played!", null,
                                        JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                        null, new Object[] { "Play other chapter", "Choose other season",
                                                "Return to PlayList Menu" },
                                        null);
                                if (op == 1) {
                                    break;
                                } else if (op == 2) {
                                    return 30;
                                }

                            } else if (op == 2) {
                                break;
                            } else if (op == 3) {
                                break;
                            } else if (op == 4) {
                                return 31;
                            } else if (op == 5) {
                                return 30;
                            }

                        }
                        if (op == 3) {
                            break;
                        }
                    }
                }

        }
        return 30;
    }

    // ----------------------------Create PlayList
    // Menu(32)-------------------------------//
    public int createPlayListMenu(int op) {
        String playListName;
        while (true) {
            playListName = JOptionPane.showInputDialog(null, "Name of the new play list:");
            if (playListName == null) {
                return 30;
            } else if (playListC.nameRepeated(playListName)) {
                JOptionPane.showMessageDialog(null, "The name already exists, try again.");
            } else if (!playListName.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")) {
                JOptionPane.showMessageDialog(null, "Invalid PlayListName, try again.");
            } else {
                break;
            }
        }

        playListC.addPlayList(playListName);
        JOptionPane.showMessageDialog(null, "Playlist created successfully");
        String[] movies = userRegisteredC.getMovieNames();
        String[] series = userRegisteredC.getSerieNames();
        if (movies.length > 0 || series.length > 0) {
            op = JOptionPane.showOptionDialog(null, "Do you want to add a serie or movie?", "Create PlayList Menu",
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, new Object[] { "Yes", "No" }, null);
            if (op == 1) {
                return 30;
            }

            while (true && op == 0) {
                while (true) {
                    if (movies.length == 0) {
                        JOptionPane.showMessageDialog(null, "There are not movies to add");
                        break;
                    }
                    try {
                        String selectedMovieName = (String) JOptionPane.showInputDialog(
                                null,
                                "Choose a movie",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                movies,
                                null);
                        if (selectedMovieName == null) {
                            break;
                        }
                        String[] aux = selectedMovieName.split("-");
                        playListC.addMovieToPlayList(playListName, Integer.parseInt(aux[1]));
                        if (movies.length == 1) {
                            JOptionPane.showMessageDialog(null, "There is not more movies to add");
                            break;
                        }
                        op = JOptionPane.showOptionDialog(null, "Do you want to add another movie?",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                null, new Object[] { "Yes", "No" }, null);
                        if (op == 1) {
                            break;
                        }

                        movies = playListC.updateNames(movies, selectedMovieName);
                    } catch (Exception e) {
                        break;
                    }
                }

                while (true) {
                    if (series.length == 0) {
                        JOptionPane.showMessageDialog(null, "There are not series to add");
                        break;
                    }
                    try {
                        String selectedSerieName = (String) JOptionPane.showInputDialog(
                                null,
                                "Choose a serie",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                series,
                                null);
                        if (selectedSerieName == null) {
                            break;
                        }

                        String[] aux = selectedSerieName.split("-");
                        playListC.addSerieToPlayList(playListName, Integer.parseInt(aux[1]));

                        if (series.length == 1) {
                            JOptionPane.showMessageDialog(null, "There is not more movies to add");
                            break;
                        }
                        op = JOptionPane.showOptionDialog(null, "Do you want to add another serie?",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                null, new Object[] { "Yes", "No" }, null);
                        if (op == 1) {
                            break;
                        }

                        series = playListC.updateNames(series, selectedSerieName);
                    } catch (Exception e) {
                        break;
                    }

                }
                break;
            }

            JOptionPane.showMessageDialog(null, "All the updates were done successfully!");
        }

        return 30;
    }

    // ----------------------------Update PlayList
    // Menu(32)-------------------------------//
    public int updatePlayListMenu(int op) {
        if (userRegisteredC.getCurrentUser().getplayList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not PlayList created yet");
            return 30;
        }
        String playListName = (String) JOptionPane.showInputDialog(null, "Choose a PlayList", "Update Play List Menu",
                JOptionPane.PLAIN_MESSAGE,
                null, playListC.playListNames(), null);
        if (playListName == null) {
            return 30;
        }

        playListC.playListFound(playListName);

        String[] moviesPlayList = playListC.playListMovieNames();
        String[] moviesToAdd = playListC.moviesToAdd();
        JComboBox<String> playlistMovies = new JComboBox<>(moviesPlayList);
        playlistMovies.setBackground(new Color(255, 114, 114));
        JComboBox<String> allMovies = new JComboBox<>(moviesToAdd);
        allMovies.setBackground(new Color(64, 203, 255));

        String[] seriesPlayList = playListC.playListSerieNames();
        String[] seriesToAdd = playListC.seriesToAdd();
        JComboBox<String> playlistSeries = new JComboBox<>(seriesPlayList);
        playlistSeries.setBackground(new Color(255, 165, 65));
        JComboBox<String> allSeries = new JComboBox<>(seriesToAdd);
        allSeries.setBackground(new Color(219, 136, 255));

        // Labels
        JLabel l1 = new JLabel("New name of the play list:");
        l1.setBackground(new Color(233, 255, 71));
        JLabel l2 = new JLabel("Your current movies in PlayList:");
        l2.setBackground(new Color(255, 114, 114));
        JLabel l3 = new JLabel("The PlayList does not have movies added yet");
        l3.setBackground(new Color(255, 114, 114));
        JLabel l4 = new JLabel("Choose a movie to add:");
        l4.setBackground(new Color(64, 203, 255));
        JLabel l5 = new JLabel("There are not movies to add");
        l5.setBackground(new Color(64, 203, 255));
        JLabel l6 = new JLabel("Your current series in PlayList:");
        l6.setBackground(new Color(255, 165, 65));
        JLabel l7 = new JLabel("Choose a serie to add:");
        l7.setBackground(new Color(219, 136, 255));
        JLabel l8 = new JLabel("The PlayList does not have series added yet");
        l8.setBackground(new Color(255, 165, 65));
        JLabel l9 = new JLabel("There are not series to add");
        l9.setBackground(new Color(219, 136, 255));
        l1.setOpaque(true);
        l2.setOpaque(true);
        l3.setOpaque(true);
        l4.setOpaque(true);
        l5.setOpaque(true);
        l6.setOpaque(true);
        l7.setOpaque(true);
        l8.setOpaque(true);
        l9.setOpaque(true);

        JTextField nameField = new JTextField();
        nameField.setBackground(new Color(233, 255, 71));

        while (true) {

            JPanel panel1 = new JPanel(new GridLayout(5, 2, 5, 5));

            // Box of movies in playList
            if (moviesPlayList.length > 0) {
                nameField.setText(playListC.getCurrentPlayList().getName());
                panel1.add(l1);
                panel1.add(nameField);
                panel1.add(l2);
                panel1.add(playlistMovies);
            } else {
                nameField.setText(playListC.getCurrentPlayList().getName());
                panel1.add(l1);
                panel1.add(nameField);
                panel1.add(l3);
                panel1.add(new JLabel());
            }

            // Box of all movies avalilable
            if (moviesToAdd.length > 0) {
                // There are not movies in PlayList and there are movies to add
                panel1.add(l4);
                panel1.add(allMovies);

            } else {
                // There are not movies in PlayList and there are not movies to add
                panel1.add(l5);
                panel1.add(new JLabel());
            }

            // Box of series in playList
            if (seriesPlayList.length > 0) {
                panel1.add(l6);
                panel1.add(playlistSeries);

            } else {
                panel1.add(l8);
                panel1.add(new JLabel());
            }

            // Box of all series available
            if (seriesToAdd.length > 0) {
                panel1.add(l7);
                panel1.add(allSeries);
            } else {
                panel1.add(l9);
                panel1.add(new JLabel());
            }

            int optionResult = JOptionPane.showOptionDialog(
                    null,
                    panel1,
                    "Choose a movie",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] {
                            "<html><font color='#A29F00'>Change name</font></html>",
                            "<html><font color='#FF0000'>Remove Movie Selected</font></html>",
                            "<html><font color='#0070FF'>Add Movie to PlayList</font></html>",
                            "<html><font color='#FF8F00'>Remove Serie Selected</font></html>",
                            "<html><font color='#E800FF'>Add Serie to PlayList</font></html>",
                            "Return"
                    },
                    null);

            if (optionResult == 5) {
                break;
            }
            switch (optionResult) {
                // Modifies the name of the play list
                case 0:
                    if(!nameField.getText().matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$")){
                        JOptionPane.showMessageDialog(null, "Name invalid");
                        break;
                    }else if (playListC.nameRepeated(nameField.getText()) ) {
                        JOptionPane.showMessageDialog(null, "Name already exists, try again.");
                        break;
                    } else {
                        playListC.updateNamePlayList(playListName, nameField.getText());
                        JOptionPane.showMessageDialog(null, "Name Changed Successfully!");
                        playListName = nameField.getText();
                    }
                    break;
                // Removes a movie from current playlist
                case 1:
                    if ((String) playlistMovies.getSelectedItem() != null) {
                        playListC.removeMovie((String) playlistMovies.getSelectedItem());
                    }
                    break;
                // Add movie at PlayList
                case 2:
                    if ((String) allMovies.getSelectedItem() != null) {
                        String[] aux = String.valueOf(allMovies.getSelectedItem()).split("-");
                        playListC.addMovieToPlayList(playListName, Integer.parseInt(aux[1]));
                    }
                    break;
                case 3:
                    if ((String) playlistSeries.getSelectedItem() != null) {
                        playListC.removeSerie((String) playlistSeries.getSelectedItem());
                    }
                    break;
                case 4:
                    if ((String) allSeries.getSelectedItem() != null) {
                        String[] auxx = String.valueOf(allSeries.getSelectedItem()).split("-");
                        playListC.addSerieToPlayList(playListName, Integer.parseInt(auxx[1]));
                    }
                    break;

            }
            moviesPlayList = playListC.playListMovieNames();
            playlistMovies = new JComboBox<>(moviesPlayList);
            playlistMovies.setBackground(new Color(255, 114, 114));
            moviesToAdd = playListC.moviesToAdd();
            allMovies = new JComboBox<>(moviesToAdd);
            allMovies.setBackground(new Color(64, 203, 255));

            seriesPlayList = playListC.playListSerieNames();
            seriesToAdd = playListC.seriesToAdd();
            playlistSeries = new JComboBox<>(seriesPlayList);
            playlistSeries.setBackground(new Color(255, 165, 65));
            allSeries = new JComboBox<>(seriesToAdd);
            allSeries.setBackground(new Color(219, 136, 255));

        }
        return 30;
    }

    // --------------------Remove PlayList(34)--------------------//
    public int removePlayListMenu(int op) {
        if (playListC.getCurrentUser().getplayList().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not PlayList created");
            return 30;
        }
        String playListName = (String) JOptionPane.showInputDialog(null, "Choose a PlayList to remove",
                "Remove Play List Menu", JOptionPane.PLAIN_MESSAGE,
                null, playListC.playListNames(), null);
        if (playListName == null) {
            return 30;
        }

        playListC.removePlayList(playListName);
        JOptionPane.showMessageDialog(null, playListName + " removed successfully!");
        return 30;
    }

    // ----------------------------Category
    // Menu(35)-------------------------------//
    public int categoryMenu(int op) {
        op = JOptionPane.showOptionDialog(null, "Choose an option to management the categories", "Category Menu",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                null, new Object[] { "See", "Create", "Update", "Remove", "Return" }, null);

        switch (op) {
            case 0:
                op = 36;
                break;
            case 1:
                op = 37;
                break;
            case 2:
                op = 38;
                break;
            case 3:
                op = 39;
                break;
            case 4:
                op = 4;
                break;
        }

        return op;
    }

    // ----------------------------See Category
    // Menu(36)-------------------------------//
    public int seeCategorytMenu(int op) {
        if (categoryC.getCategories().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not categories created yet");
            return 35;
        }

        String categoryName = (String) JOptionPane.showInputDialog(null, "Choose a category", "Category Menu",
                JOptionPane.PLAIN_MESSAGE,
                null, categoryC.categoriesNames(), null);

        if (categoryName == null) {
            return 35;
        }
        categoryC.categoryFound(categoryName);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        String[] moviesCategory = categoryC.categoryMovieNames();
        JComboBox<String> categoryMovies = new JComboBox<>(moviesCategory);

        String[] seriesCategory = categoryC.categorySerieNames();
        JComboBox<String> categorySeries = new JComboBox<>(seriesCategory);

        if (moviesCategory.length > 0) {
            panel.add(new JLabel("Movies:"));
            panel.add(categoryMovies);
        } else {
            panel.add(new JLabel("Movies:"));
            panel.add(new JLabel("There are not movies added yet"));
        }

        if (seriesCategory.length > 0) {
            panel.add(new JLabel("Series:"));
            panel.add(categorySeries);
        } else {
            panel.add(new JLabel("Series:"));
            panel.add(new JLabel("There are not series added yet"));
        }

        while (true) {
            op = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Choose a movie",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] { "See movie information", "See serie information", "return" },
                    null);
            if (op == 0) {
                if (moviesCategory.length > 0) {
                    String[] aux = String.valueOf(categoryMovies.getSelectedItem()).split("-");
                    JOptionPane.showMessageDialog(null,
                            "Name: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getName() +
                                    "\nDescription: "
                                    + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDescription() +
                                    "\nauthor: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getAuthor() +
                                    "\nDuration: " + userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration());
                }
            } else if (op == 1) {
                if (seriesCategory.length > 0) {
                    String[] aux = String.valueOf(categorySeries.getSelectedItem()).split("-");
                    JOptionPane.showMessageDialog(null,
                            "Name: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getName() +
                                    "\nDescription: "
                                    + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getDescription() +
                                    "\nauthor: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getAuthor());

                }
            } else if (op == 2) {
                break;
            }
        }
        return 35;
    }

    // ----------------------------Create Category
    // Menu(32)-------------------------------//
    public int createCategoryMenu(int op) {
        String categoryName;
        while (true) {
            categoryName = JOptionPane.showInputDialog(null, "Name of the new category:");
            
            if (categoryName == null) {
                return 35;
            } else if (categoryC.nameRepeated(categoryName)) {
                JOptionPane.showMessageDialog(null, "The name already exists, try again.");
            } else if (!categoryName.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                JOptionPane.showMessageDialog(null, "Invalid category, try again.");
            } else {
                break;
            }
        }

        categoryC.addCategory(categoryName.trim());
        JOptionPane.showMessageDialog(null, "Category created successfully");

        String[] movies = userRegisteredC.getMovieNames();
        String[] series = userRegisteredC.getSerieNames();

        if (movies.length > 0 || series.length > 0) {
            op = JOptionPane.showOptionDialog(null, "Do you want to add a serie or movie?", "Create Category Menu",
                    JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                    null, new Object[] { "Yes", "No" }, null);
            if (op == 1) {
                return 35;
            }

            while (true && op == 0) {
                while (true) {
                    if (movies.length == 0) {
                        JOptionPane.showMessageDialog(null, "There are not movies to add");
                        break;
                    }
                    try {
                        String selectedMovieName = (String) JOptionPane.showInputDialog(
                                null,
                                "Choose a movie",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                movies,
                                null);
                        if (selectedMovieName == null) {
                            break;
                        }
                        String[] aux = selectedMovieName.split("-");

                        categoryC.addMovieToCategory(categoryName, Integer.parseInt(aux[1]));

                        if (movies.length == 1) {
                            JOptionPane.showMessageDialog(null, "There is not more movies to add");
                            break;
                        }
                        op = JOptionPane.showOptionDialog(null, "Do you want to add another movie?",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                null, new Object[] { "Yes", "No" }, null);
                        if (op == 1) {
                            break;
                        }

                        movies = categoryC.updateNames(movies, selectedMovieName);
                    } catch (Exception e) {
                        break;
                    }
                }

                while (true) {
                    if (series.length == 0) {
                        JOptionPane.showMessageDialog(null, "There are not series to add");
                        break;
                    }
                    try {
                        String selectedSerieName = (String) JOptionPane.showInputDialog(
                                null,
                                "Choose a serie",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                series,
                                null);
                        if (selectedSerieName == null) {
                            break;
                        }

                        String[] aux = selectedSerieName.split("-");
                        categoryC.addSerieToCategory(categoryName, Integer.parseInt(aux[1]));

                        if (series.length == 1) {
                            JOptionPane.showMessageDialog(null, "There is not more movies to add");
                            break;
                        }
                        op = JOptionPane.showOptionDialog(null, "Do you want to add another serie?",
                                "Create PlayList Menu",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                null, new Object[] { "Yes", "No" }, null);
                        if (op == 1) {
                            break;
                        }

                        series = categoryC.updateNames(series, selectedSerieName);
                    } catch (Exception e) {
                        break;
                    }

                }
                break;
            }

            JOptionPane.showMessageDialog(null, "All the updates were done successfully!");
        }

        return 35;
    }

    // ----------------------------Update Category
    // Menu(32)-------------------------------//
    public int updateCategoryMenu(int op) {
        if (categoryC.getCategories().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not categories created yet");
            return 35;
        }

        String categoryName = (String) JOptionPane.showInputDialog(null, "Choose a Category", "Update Category Menu",
                JOptionPane.PLAIN_MESSAGE,
                null, categoryC.categoriesNames(), null);
        if (categoryName == null) {
            return 35;
        }

        categoryC.categoryFound(categoryName);

        String[] moviesCategory = categoryC.categoryMovieNames();
        String[] moviesToAdd = categoryC.moviesToAdd();
        JComboBox<String> categoryMovies = new JComboBox<>(moviesCategory);
        categoryMovies.setBackground(new Color(255, 114, 114));
        JComboBox<String> allMovies = new JComboBox<>(moviesToAdd);
        allMovies.setBackground(new Color(64, 203, 255));

        String[] seriesPlayList = categoryC.categorySerieNames();
        String[] seriesToAdd = categoryC.seriesToAdd();
        JComboBox<String> playlistSeries = new JComboBox<>(seriesPlayList);
        playlistSeries.setBackground(new Color(255, 165, 65));
        JComboBox<String> allSeries = new JComboBox<>(seriesToAdd);
        allSeries.setBackground(new Color(219, 136, 255));

        // Labels
        JLabel l1 = new JLabel("New name of the category:");
        l1.setBackground(new Color(233, 255, 71));
        JLabel l2 = new JLabel("Current movies in " + categoryName);
        l2.setBackground(new Color(255, 114, 114));
        JLabel l3 = new JLabel("The category does not have movies added yet");
        l3.setBackground(new Color(255, 114, 114));
        JLabel l4 = new JLabel("Choose a movie to add:");
        l4.setBackground(new Color(64, 203, 255));
        JLabel l5 = new JLabel("There are not movies to add");
        l5.setBackground(new Color(64, 203, 255));
        JLabel l6 = new JLabel("Current series in " + categoryName);
        l6.setBackground(new Color(255, 165, 65));
        JLabel l7 = new JLabel("Choose a serie to add:");
        l7.setBackground(new Color(219, 136, 255));
        JLabel l8 = new JLabel("The category does not have series added yet");
        l8.setBackground(new Color(255, 165, 65));
        JLabel l9 = new JLabel("There are not series to add");
        l9.setBackground(new Color(219, 136, 255));
        l1.setOpaque(true);
        l2.setOpaque(true);
        l3.setOpaque(true);
        l4.setOpaque(true);
        l5.setOpaque(true);
        l6.setOpaque(true);
        l7.setOpaque(true);
        l8.setOpaque(true);
        l9.setOpaque(true);

        JTextField nameField = new JTextField();
        nameField.setBackground(new Color(233, 255, 71));

        while (true) {

            JPanel panel1 = new JPanel(new GridLayout(5, 2, 5, 5));

            // Box of movies in playList
            if (moviesCategory.length > 0) {
                nameField.setText(categoryC.getCurrentCategory().getName());
                panel1.add(l1);
                panel1.add(nameField);
                panel1.add(l2);
                panel1.add(categoryMovies);
            } else {
                nameField.setText(categoryC.getCurrentCategory().getName());
                panel1.add(l1);
                panel1.add(nameField);
                panel1.add(l3);
                panel1.add(new JLabel());
            }

            // Box of all movies avalilable
            if (moviesToAdd.length > 0) {
                panel1.add(l4);
                panel1.add(allMovies);

            } else {
                panel1.add(l5);
                panel1.add(new JLabel());
            }

            // Box of series in playList
            if (seriesPlayList.length > 0) {
                panel1.add(l6);
                panel1.add(playlistSeries);

            } else {
                panel1.add(l8);
                panel1.add(new JLabel());
            }

            // Box of all series available
            if (seriesToAdd.length > 0) {
                panel1.add(l7);
                panel1.add(allSeries);
            } else {
                panel1.add(l9);
                panel1.add(new JLabel());
            }

            op = JOptionPane.showOptionDialog(
                    null,
                    panel1,
                    "Choose a movie",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] {
                            "<html><font color='#A29F00'>Change name</font></html>",
                            "<html><font color='#FF0000'>Remove Movie Selected</font></html>",
                            "<html><font color='#0070FF'>Add Movie to PlayList</font></html>",
                            "<html><font color='#FF8F00'>Remove Serie Selected</font></html>",
                            "<html><font color='#E800FF'>Add Serie to PlayList</font></html>",
                            "Return"
                    },
                    null);

            if (op == 5) {
                break;
            }
            switch (op) {
                // Modifies the name of the play list
                case 0:
                    if(!nameField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")){
                        JOptionPane.showMessageDialog(null, "Name invalid");
                        break;
                    }else if (categoryC.nameRepeated(nameField.getText())) {
                        JOptionPane.showMessageDialog(null, "Name already exists, try again.");
                        break;
                    } else {
                        categoryC.updateNameCategory(categoryName, nameField.getText());
                        JOptionPane.showMessageDialog(null, "Name Changed Successfully!");
                        categoryName = nameField.getText();
                    }
                    break;
                // Removes a movie from current playlist
                case 1:
                    if ((String) categoryMovies.getSelectedItem() != null) {
                        categoryC.removeMovie((String) categoryMovies.getSelectedItem());
                    }
                    break;
                // Add movie at PlayList
                case 2:
                    if ((String) allMovies.getSelectedItem() != null) {
                        String[] aux = String.valueOf(allMovies.getSelectedItem()).split("-");
                        categoryC.addMovieToCategory(categoryName, Integer.parseInt(aux[1]));
                    }
                    break;
                case 3:
                    if ((String) playlistSeries.getSelectedItem() != null) {
                        categoryC.removeSerie((String) playlistSeries.getSelectedItem());
                    }
                    break;
                case 4:
                    if ((String) allSeries.getSelectedItem() != null) {
                        String[] auxx = String.valueOf(allSeries.getSelectedItem()).split("-");
                        categoryC.addSerieToCategory(categoryName, Integer.parseInt(auxx[1]));
                    }
                    break;

            }

            moviesCategory = categoryC.categoryMovieNames();
            moviesToAdd = categoryC.moviesToAdd();
            categoryMovies = new JComboBox<>(moviesCategory);
            categoryMovies.setBackground(new Color(255, 114, 114));
            allMovies = new JComboBox<>(moviesToAdd);
            allMovies.setBackground(new Color(64, 203, 255));

            seriesPlayList = categoryC.categorySerieNames();
            seriesToAdd = categoryC.seriesToAdd();
            playlistSeries = new JComboBox<>(seriesPlayList);
            playlistSeries.setBackground(new Color(255, 165, 65));
            allSeries = new JComboBox<>(seriesToAdd);
            allSeries.setBackground(new Color(219, 136, 255));

        }
        return 35;
    }

    // --------------------Remove Category(41)--------------------//
    public int removeCategoryMenu(int op) {
        if (categoryC.getCategories().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not categories created");
            return 35;
        }
        String categoryName = (String) JOptionPane.showInputDialog(null, "Choose a category to remove",
                "Remove Category Menu", JOptionPane.PLAIN_MESSAGE,
                null, categoryC.categoriesNames(), null);
        if (categoryName == null) {
            return 35;
        }

        categoryC.removeCategory(categoryName);
        JOptionPane.showMessageDialog(null, categoryName + " removed successfully!");
        return 35;
    }

    public int userSubMenu(int op) {
        if (subsC.getSubscriptions().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not subscriptions added yet");
            return 20;
        }
        while (true) {
            JPanel panel = new JPanel(new GridLayout(2, 2));
            String[] subsAvailable = subsC.subsAvailablesNames(userRegisteredC.getCurrentUser().getSub());
            JComboBox<String> subsAvailableBox = new JComboBox<>(subsAvailable);

            if (subsAvailable.length > 0) {
                panel.add(new JLabel("Subscriptions:"));
                panel.add(subsAvailableBox);
            } else {
                panel.add(new JLabel("Subscriptions:"));
                panel.add(new JLabel("There are not more subscriptions added yet"));
            }

            if (userRegisteredC.getCurrentUser().getSub() != null) {
                panel.add(new JLabel("Current Subscription:"));
                panel.add(new JLabel(userRegisteredC.getCurrentUser().getSub().getName()));
            } else {
                panel.add(new JLabel("Current Subscription:"));
                panel.add(new JLabel("You are not subscribed"));
            }
            op = JOptionPane.showOptionDialog(
                    null,
                    panel, // Puedes pasar el panel directamente como el componente de entrada
                    "User Subscriptions Menu",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] { "Subscribe", "Cancel Subscription", "return" },
                    null);

            if (op == 0) {
                subsC.findCurrentSubscription((String) subsAvailableBox.getSelectedItem());
                if (userRegisteredC.getCurrentUser().getSub() == null) {
                    op = JOptionPane.showOptionDialog(null,
                                        "Name: " + subsC.getCurrentSub().getName() +
                                        "\nDescription: " + subsC.getCurrentSub().getDescription() + 
                                        "\nPrice: " + subsC.getCurrentSub().getPrice() +  
                                        "\nDuration: " + subsC.getCurrentSub().getDuration() + 
                                        "\nAre you sure do you want to susbribe?", "Confirmation Menu",JOptionPane.PLAIN_MESSAGE,
                                        JOptionPane.PLAIN_MESSAGE,null,new Object[]{"Yes","No"}, null);
                    if(op == 0){
                        subsC.addSubToUser(subsC.getCurrentSub(), userRegisteredC.getCurrentUser());
                        JOptionPane.showMessageDialog(null, "Subscription added successfully");
                    }
                }
            } else if (op == 1) {
                if (userRegisteredC.getCurrentUser().getSub() != null) {
                    subsC.cancelSub(userRegisteredC.getCurrentuser());
                    JOptionPane.showMessageDialog(null, "Subscription cancelled successfully");
                }
            } else if (op == 2) {
                break;
            }

            um.updateUsers(userRegisteredC.getAllUsers());
        }
        return 20;
    }

    // ----------------------------User Category
    // Menu(41)-------------------------------//
    public int userCategorytMenu(int op) {
        if (categoryC.getCategories().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are not categories created yet");
            return 20;
        }

        String categoryName = (String) JOptionPane.showInputDialog(null, "Choose a category", "User Category Menu",
                JOptionPane.PLAIN_MESSAGE,
                null, categoryC.categoriesNames(), null);

        if (categoryName == null) {
            return 20;
        }
        categoryC.categoryFound(categoryName);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        String[] moviesCategory = categoryC.categoryMovieNames();
        JComboBox<String> categoryMovies = new JComboBox<>(moviesCategory);

        String[] seriesCategory = categoryC.categorySerieNames();
        JComboBox<String> categorySeries = new JComboBox<>(seriesCategory);

        if (moviesCategory.length > 0) {
            panel.add(new JLabel("Movies:"));
            panel.add(categoryMovies);
        } else {
            panel.add(new JLabel("Movies:"));
            panel.add(new JLabel("There are not movies added yet"));
        }

        if (seriesCategory.length > 0) {
            panel.add(new JLabel("Series:"));
            panel.add(categorySeries);
        } else {
            panel.add(new JLabel("Series:"));
            panel.add(new JLabel("There are not series added yet"));
        }

        while (true) {
            op = JOptionPane.showOptionDialog(
                    null,
                    panel,
                    "Choose a movie",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[] { "See movie", "See serie", "return" },
                    null);
            if (op == 0) {
                if (moviesCategory.length > 0) {
                    LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
                    if (userRegisteredC.getCurrentUser().getSub() != null) {
                        if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub()
                                .getEndTime()) {
                            userRegisteredC.getCurrentUser().setSub(null);
                        }
                    }
                    String[] aux = ((String) (categoryMovies.getSelectedItem())).split("-");

                    if (userRegisteredC.getCurrentUser().getSub() == null) {
                        int add = (int) (Math.random() * 5);
                        ImageIcon addPic = (ImageIcon) addsPics[add];
                        JFrame addFrame = new JFrame("Publicity Space");
                        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int x = (screenSize.width - addFrame.getWidth()) / 2;
                        int y = (screenSize.height - addFrame.getHeight()) / 2;
                        addFrame.setLocation(x, y);

                        JPanel addPanel = new JPanel(new BorderLayout());
                        JProgressBar progressBar = new JProgressBar(0, 100);
                        progressBar.setStringPainted(true);
                        addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                        addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                        addPanel.add(progressBar, BorderLayout.SOUTH);
                        addFrame.add(addPanel);

                        addFrame.setVisible(true);

                        for (int i = 0; i <= 100; i++) {
                            progressBar.setValue(i);
                            try {
                                Thread.sleep((long) (3000 * 0.01));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        addFrame.dispose();
                    }
                    long duration = userRegisteredC.getMovie(Integer.parseInt(aux[1])).getDuration();

                    JFrame frame = new JFrame("Playing Movie");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(300, 150);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (screenSize.width - frame.getWidth()) / 2;
                    int y = (screenSize.height - frame.getHeight()) / 2;
                    frame.setLocation(x, y);

                    JPanel moviePanel = new JPanel(new BorderLayout());
                    JProgressBar progressBar = new JProgressBar(0, 100);
                    progressBar.setStringPainted(true);

                    moviePanel.add(progressBar, BorderLayout.CENTER);

                    frame.add(moviePanel);

                    frame.setVisible(true);

                    for (int i = 0; i <= 100; i++) {
                        progressBar.setValue(i);
                        try {
                            Thread.sleep((long) (duration * 0.01));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    frame.dispose();

                    op = JOptionPane.showOptionDialog(null, "Movie played!", null, JOptionPane.PLAIN_MESSAGE,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            new Object[] { "Choose other movie", "Return" },
                            null);
                    if (op == 1) {
                        return 20;
                    }
                }
            } else if (op == 1) {
                if (seriesCategory.length > 0) {

                    String[] aux = String.valueOf(categorySeries.getSelectedItem()).split("-");
                    op = JOptionPane.showOptionDialog(null,
                            "Serie name: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getName() +
                                    "\nDescription: "
                                    + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getDescription() +
                                    "\nAuthor: " + userRegisteredC.getSerie(Integer.parseInt(aux[1])).getAuthor(),
                            "See Movie ", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                            null, new Object[] { "See Seasons", "choose other serie" },
                            null);

                    if (op == 0) {
                        while (true) {

                            String seasonName = (String) JOptionPane.showInputDialog(null, "Choose a season",
                                    "Season Menu", JOptionPane.PLAIN_MESSAGE,
                                    null, userRegisteredC.serieSeasonsNames(Integer.parseInt(aux[1])), null);
                            if (seasonName == null) {
                                break;
                            }
                            while (true) {
                                LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
                                if (userRegisteredC.getCurrentUser().getSub() != null) {
                                    if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser()
                                            .getSub().getEndTime()) {
                                        userRegisteredC.getCurrentUser().setSub(null);
                                    }
                                }
                                String chapterName = (String) JOptionPane.showInputDialog(null, "Chose a chapter",
                                        "Chapter Menu", JOptionPane.PLAIN_MESSAGE,
                                        null, userRegisteredC.serieChapterNames(Integer.parseInt(aux[1]), seasonName),
                                        null);
                                if (chapterName == null) {
                                    break;
                                }
                                op = JOptionPane.showOptionDialog(null, "Chapter name: " + chapterName +
                                        "\nDuration: "
                                        + userRegisteredC
                                                .getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                                .getDuration()
                                        + "\nDescription: "
                                        + userRegisteredC
                                                .getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                                .getDescription(),
                                        "See Movie ", JOptionPane.PLAIN_MESSAGE,
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        new Object[] { "Play Chapter", "choose other chapter", "choose other season",
                                                "choose other serie" },
                                        null);

                                if (op == 0) {
                                    if (userRegisteredC.getCurrentUser().getSub() == null) {
                                        int add = (int) (Math.random() * 5);
                                        ImageIcon addPic = (ImageIcon) addsPics[add];
                                        JFrame addFrame = new JFrame("Publicity Space");
                                        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        addFrame.setSize(addPic.getIconWidth(), addPic.getIconHeight());
                                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                        int x = (screenSize.width - addFrame.getWidth()) / 2;
                                        int y = (screenSize.height - addFrame.getHeight()) / 2;
                                        addFrame.setLocation(x, y);

                                        JPanel addPanel = new JPanel(new BorderLayout());
                                        JProgressBar progressBar = new JProgressBar(0, 100);
                                        progressBar.setStringPainted(true);
                                        addPanel.add(new JLabel(adds[add]), BorderLayout.NORTH);
                                        addPanel.add(new JLabel(addPic), BorderLayout.CENTER);
                                        addPanel.add(progressBar, BorderLayout.SOUTH);
                                        addFrame.add(addPanel);

                                        addFrame.setVisible(true);

                                        for (int i = 0; i <= 100; i++) {
                                            progressBar.setValue(i);
                                            try {
                                                Thread.sleep((long) (3000 * 0.01));
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        addFrame.dispose();
                                    }

                                    JFrame frame = new JFrame("Playing Chap");
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setSize(300, 150);
                                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                    int x = (screenSize.width - frame.getWidth()) / 2;
                                    int y = (screenSize.height - frame.getHeight()) / 2;
                                    frame.setLocation(x, y);

                                    JPanel chapterPanel = new JPanel(new BorderLayout());
                                    JProgressBar progressBar = new JProgressBar(0, 100);
                                    progressBar.setStringPainted(true);

                                    chapterPanel.add(progressBar, BorderLayout.CENTER);
                                    frame.add(chapterPanel);

                                    frame.setVisible(true);
                                    int duration = userRegisteredC
                                            .getSerieChapter(Integer.parseInt(aux[1]), seasonName, chapterName)
                                            .getDuration();

                                    for (int i = 0; i < 100; i++) {

                                        progressBar.setValue(i);
                                        try {
                                            Thread.sleep((int) (duration * 0.01));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    frame.dispose();
                                    op = JOptionPane.showOptionDialog(null, "chapter played!", null,
                                            JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            new Object[] { "Play other chapter", "choose other serie",
                                                    "Choose other season" },
                                            null);

                                    if (op == 1) {
                                        return 41;
                                    } else if (op == 2) {
                                        break;
                                    }
                                } else if (op == 2) {
                                    break;
                                } else if (op == 3) {
                                    return 41;
                                }
                            }
                        }
                    }
                }
            } else if (op == 2) {
                break;
            }
        }
        return 20;
    }

}
