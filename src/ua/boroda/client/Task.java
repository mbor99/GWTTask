package ua.boroda.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Task implements EntryPoint {

    ArrayList<User> users = new ArrayList<User>();

    IdColumn idCol;
    NameColumn nameCol;
    RoleColumn roleCol;

    ListBox dropBox = new ListBox(false);
    List<String> list =new LinkedList();
    public void init(){
        list.add(String.valueOf(User.role.admin));
        list.add(String.valueOf(User.role.user));
    }


    @Override
    public void onModuleLoad() {
        init();

        users.add(new User("1", "Vasya", "Pupkin", "vp@mail.com", User.role.admin));
        users.add(new User("2", "Ivan", "Petrov", "ip@mail.com", User.role.user));

        SplitLayoutPanel p = new SplitLayoutPanel();
        p.setPixelSize(800, 600);
        p.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        p.getElement().getStyle().setBorderWidth(5, Style.Unit.PX);
        p.getElement().getStyle().setBorderColor("grey");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-VDragger"
                + "{ height: 5px !important; background: gray; }");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-HDragger"
                + "{ width: 5px !important; background: gray; }");
        p.addNorth(grid(), 250);
        p.addWest(new HTML("info"), 400);
        p.add(new HTML("additional field"));


        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(p);

    }


    public DataGrid grid() {
        // Create a DataGrid.
        DataGrid dataGrid = new DataGrid<User>();
        dataGrid.setWidth("100%");

        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("empty datagrid"));

        // Add a selection model so we can select cells.
        final SelectionModel<User> selectionModel = new NoSelectionModel<User>();
        dataGrid.setSelectionModel(selectionModel, DefaultSelectionEventManager
                .<User>createCheckboxManager());


        idCol = new IdColumn();
        dataGrid.addColumn(idCol, "ID");
        dataGrid.setColumnWidth(idCol, "25%");

        nameCol = new NameColumn();
        dataGrid.addColumn(nameCol, "Name");
        dataGrid.setColumnWidth(nameCol, "35%");

        roleCol = new RoleColumn();
        dataGrid.addColumn(roleCol, "Role");
        dataGrid.setColumnWidth(roleCol, "40%");

        dataGrid.setRowData(users);


        return dataGrid;
    }


    private class IdColumn extends TextColumn<User> {
        @Override
        public String getValue(User user) {
            return user.getId();
        }
    }

    private class NameColumn extends TextColumn<User> {
        @Override
        public String getValue(User user) {
            return user.getName();
        }
    }

    private class RoleColumn extends TextColumn<User> {
        @Override
        public String getValue(User user) {
            return String.valueOf(user.getR());
        }
    }


//
//    SelectionCell roleCell = new SelectionCell(list);
//
//    Column<User, SelectionCell> roleColumn = new Column<User, SelectionCell>(new HTMLTable.Cell)
//    {
////        @Override
////        public Object getValue(Object p) {
////            ListBox dropBox = new ListBox(false);
////            dropBox.addItem(String.valueOf(User.role.admin));
////            dropBox.addItem(String.valueOf(User.role.user));
////            SelectionCell selectionCell= new SelectionCell((List<String>) dropBox) { };
////            return selectionCell;
////        }
//
//        @Override
//        public SelectionCell getValue(User object) {
//
//            SelectionCell selectionCell= new SelectionCell((List<String>) dropBox) { };
//
//            return selectionCell;
//        }
//    };




}
