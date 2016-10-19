package controllers;

import play.data.Form;
import play.mvc.*;
import services.Product;
import views.html.products.details;
import views.html.products.list;

import java.util.List;

public class Products extends Controller {
    public  Result list(){
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public  Result newProduct(){
        final Form<Product> productForm = Form.form(Product.class);
        return ok(details.render(productForm));
    }

    public  Result details(String ean){
        final Form<Product> productForm = Form.form(Product.class);
        final Product product = Product.findByEan(ean);
        if(product == null){
            return notFound(String.format("Product %s does not exist.", ean));
        }

        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    public Result delete(String ean){
        final Form<Product> productForm = Form.form(Product.class);
        final Product product = Product.findByEan(ean);
        if(product == null){
            return notFound(String.format("Product %s does not exist.", ean));
        }

        Product.remove(product);
        return redirect(routes.Products.list());
    }

    public  Result save(){
        final Form<Product> productForm = Form.form(Product.class);
        Form<Product> boundForm = productForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }

        Product product = boundForm.get();
        product.save();
        return redirect(routes.Products.list());
    }
}
