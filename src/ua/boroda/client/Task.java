package ua.boroda.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Task implements EntryPoint {

    ArrayList<User> users = new ArrayList<User>();

    IdColumn idCol;
    NameColumn nameCol;
    RoleColumn roleCol;
    Widget infoPanel=infoPanel();
    SplitLayoutPanel p = new SplitLayoutPanel();

    ListBox dropBox = new ListBox(false);
    List<String> list =new LinkedList();

    @Override
    public void onModuleLoad() {
        users.add(new User("1", "Vasya", "Pupkin", "vp@mail.com", User.role.admin));
        users.add(new User("2", "Ivan", "Petrov", "ip@mail.com", User.role.user));
        users.add(new User("3", "Fedor", "Sidorov", "fs@mail.com", User.role.user));
        users.add(new User("4", "Petr", "Vasilyev", "pv@mail.com", User.role.admin));

        p.setPixelSize(800, 600);
        p.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        p.getElement().getStyle().setBorderWidth(5, Style.Unit.PX);
        p.getElement().getStyle().setBorderColor("grey");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-VDragger"
                + "{ height: 5px !important; background: gray; }");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-HDragger"
                + "{ width: 5px !important; background: gray; }");

        p.addNorth(grid(), 250);
        p.addWest(infoPanel, 400);
        p.add(new HTML("additional field"));

        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(p);

    }

    private Widget infoPanel() {
        VerticalPanel infoPanel = new VerticalPanel();
        Label emailInfo=new Label("Email : ");
        Label surnameInfo=new Label("Surname : ");
        infoPanel.add(emailInfo);
        infoPanel.setSpacing(20);
        infoPanel.add(surnameInfo);
        return infoPanel;
    }

    private Widget infoPanel(String email,String surname) {
        VerticalPanel infoPanel = new VerticalPanel();
        Label emailInfo=new Label("Email : "+email);
        Label surnameInfo=new Label("Surname : "+surname);
        infoPanel.add(emailInfo);
        infoPanel.setSpacing(20);
        infoPanel.add(surnameInfo);
        return infoPanel;
    }


    public DataGrid grid() {
        // Create a DataGrid.
        DataGrid dataGrid = new DataGrid<User>();
        dataGrid.setWidth("100%");

        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("empty datagrid"));


        final SingleSelectionModel<User> selectionModel =
                new SingleSelectionModel<User>();
        dataGrid.setSelectionModel(selectionModel);
        selectionModel. addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange( SelectionChangeEvent event) {
                User selected = selectionModel. getSelectedObject();
                if (selected != null) {
                    infoPanel = infoPanel(selected.getEmail(), selected.getSurname());
                    p.clear();
                    p.addNorth(grid(), 250);
                    p.addWest(infoPanel,400);
                    p.add(new HTML("additional field"));
                    }
            } });


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

//    public void rolelist(){
//
//    SelectionCell roleCell = new SelectionCell(list);
//
//    Column<User, Object> roleColumn = new Column<User, Object>(roleCell)
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
//    };//
//    }





}
