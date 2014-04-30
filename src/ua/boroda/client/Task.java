package ua.boroda.client;

import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.user.cellview.client.Column;
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
    InfoPanel infoPanel = new InfoPanel();
    SplitLayoutPanel p = new SplitLayoutPanel();
    DataGrid<User> dataGrid;
    List<String> list = new LinkedList();

    class InfoPanel extends VerticalPanel {
        final String EMAIL = "Email : ";
        final String SURNAME = "Surname : ";

        Label emailInfo = new Label(EMAIL);
        Label surnameInfo = new Label(SURNAME);

        public InfoPanel() {
            add(emailInfo);
            setSpacing(20);
            add(surnameInfo);
        }

        public void setInfo(String email, String surname) {
            emailInfo.setText(EMAIL + email);
            surnameInfo.setText(SURNAME + surname);
        }
    }

    @Override
    public void onModuleLoad() {
        list.add(0, String.valueOf(User.role.admin));
        list.add(1, String.valueOf(User.role.user));

        users.add(new User("1", "Vasya", "Pupkin", "vp@mail.com", User.role.admin));
        users.add(new User("2", "Ivan", "Petrov", "ip@mail.com", User.role.user));
        users.add(new User("3", "Fedor", "Sidorov", "fs@mail.com", User.role.user));
        users.add(new User("4", "Petr", "Vasilyev", "pv@mail.com", User.role.admin));

        p.setPixelSize(800, 600);
        p.getElement().getStyle().setBorderStyle(Style.BorderStyle.SOLID);
        p.getElement().getStyle().setBorderWidth(5, Style.Unit.PX);
        p.getElement().getStyle().setBorderColor("grey");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-VDragger" + "{ height: 5px !important; background: gray; }");
        StyleInjector.inject(".gwt-SplitLayoutPanel .gwt-SplitLayoutPanel-HDragger" + "{ width: 5px !important; background: gray; }");

        p.addNorth(grid(), 250);
        p.addWest(infoPanel, 400);
        p.add(new HTML("additional field"));

        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(p);
    }

    public DataGrid grid() {
        // Create a DataGrid.
        dataGrid = new DataGrid<User>();
        dataGrid.setWidth("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);
        dataGrid.setEmptyTableWidget(new Label("empty datagrid"));

        final SingleSelectionModel<User> selectionModel = new SingleSelectionModel<User>();
        dataGrid.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                User selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    infoPanel.setInfo(selected.getEmail(), selected.getSurname());
                }
            }
        });

        idCol = new IdColumn();
        dataGrid.addColumn(idCol, "ID");
        dataGrid.setColumnWidth(idCol, "25%");
        nameCol = new NameColumn();
        dataGrid.addColumn(nameCol, "Name");
        dataGrid.setColumnWidth(nameCol, "35%");
        SelectionCell categoryCell = new SelectionCell(list);
        Column<User, String> categoryColumn = new Column<User, String>(categoryCell) {
            @Override
            public String getValue(User object) {
                return String.valueOf(object.getR());
            }
        };
        dataGrid.addColumn(categoryColumn, "Role");
        dataGrid.setColumnWidth(categoryColumn, "40%");

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
}