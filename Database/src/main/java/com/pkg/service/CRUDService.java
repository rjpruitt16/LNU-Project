package com.pkg.service;

import com.pkg.tables.TableRow;

import java.util.List;

public interface CRUDService {
    public void create(List<Object[]> args);
    public List<Object> read(int id);
    public boolean update(int id, List<Object> object);
    public boolean delete(int id);
}
