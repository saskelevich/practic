package by.practic.ui.commands.edit.brand;

import java.util.List;

import by.practic.datalayer.IDao;
import by.practic.datalayer.entity.Brand;
import by.practic.datalayer.xml.BrandXMLDaoImpl;
import by.practic.ui.commands.AbstractCmd;
import by.practic.ui.commands.Command;

@Command(name = "list", description = "list brands")
public class CmdListBrand extends AbstractCmd {
    
    // DB
//	private IDao<Brand, Brand> dao = BrandDBDaoImpl.getInstance();
    
    // XML
    private final IDao<Brand, Brand> dao = BrandXMLDaoImpl.getInstance();
    
    @Override
    public AbstractCmd execute() {
        
        final List<Brand> brands = dao.getAll();
        
        if (brands != null) {
            brands.stream().forEach(brand -> System.out.println(brand));
        } else {
            System.out.println("List is empty");
        }
        
        return new CmdEditBrand();
    }
}