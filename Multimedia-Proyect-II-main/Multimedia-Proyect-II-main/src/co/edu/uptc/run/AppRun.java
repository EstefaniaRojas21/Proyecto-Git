package co.edu.uptc.run;

public class AppRun {
    public static void main(String[] args) {

        AppMenus menus = new AppMenus();
        int op = 0;
        while (op != -1) {
            switch (op) {
                case 0:
                    op = menus.principalMenu(op);
                    break;
                case 1:
                    op = menus.logInMenu(op);
                    break;
                case 2:
                    op = menus.registerMenu(op);
                    break;
                case 3:
                    op = menus.awayMenu(op);
                    break;
                case 4:
                    op = menus.administratorMenu(op);
                    break;
                case 5:
                    op = menus.moviesManagementMenu(op);
                    break;
                case 6:
                    op = menus.ShowMoviesMenu(op);
                    break;
                case 7:
                    op = menus.createMoviesMenu(op);
                    break;
                case 8:
                    op = menus.updateMoviesMenu(op);
                    break;
                case 9:
                    op = menus.deleteMovieMenu(op);
                    break;
                case 10:
                    op = menus.SeriesManagementMenu(op);
                    break;
                case 11:
                    op = menus.createSerieMenu(op);
                    break;
                case 12:
                    op = menus.ShowSeriesMenu(op);
                    break;
                case 13:
                    op = menus.updateSeriesMenu(op);
                    break;
                case 14:
                    op = menus.deleteSeriesMenu(op);
                    break;
                case 20:
                    op = menus.userRegisteredMenu(op);
                    break;
                case 21:
                    op = menus.ShowMovies(op);
                    break;
                case 22:
                    op = menus.ShowSeries(op);
                    break;
                case 25:
                    op = menus.subscriptionManagerMenu(op);
                    break;
                case 26:
                    op = menus.seeSubscriptionsMenu();
                    break;
                case 27:
                    op = menus.createSubscriptionsMenu(op);
                    break;
                case 28:
                    op = menus.updatesubscriptionMenu(op);
                    break;
                case 29:
                    op = menus.removeSubscriptionMenu(op);
                    break;
                case 30:
                    op = menus.playListMenu(op);
                    break;
                case 31:
                    op = menus.seePlayListMenu(op);
                    break;
                case 32:
                    op = menus.createPlayListMenu(op);
                    break;
                case 33:
                    op = menus.updatePlayListMenu(op);
                    break;
                case 34:
                    op = menus.removePlayListMenu(op);
                    break;
                case 35:
                    op = menus.categoryMenu(op);
                    break;
                case 36:
                    op = menus.seeCategorytMenu(op);
                    break;
                case 37:
                    op = menus.createCategoryMenu(op);
                    break;
                case 38:
                    op = menus.updateCategoryMenu(op);
                    break;
                case 39:
                    op = menus.removeCategoryMenu(op);
                    break;
                case 40:
                    op = menus.userSubMenu(op);
                    break;
                case 41:
                    op = menus.userCategorytMenu(op);
                    break;
            }
        }

    }
}
