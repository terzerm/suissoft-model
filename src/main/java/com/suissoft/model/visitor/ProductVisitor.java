package com.suissoft.model.visitor;

import com.suissoft.model.entity.Entity;
import com.suissoft.model.entity.product.Order;
import com.suissoft.model.entity.product.Product;
import com.suissoft.model.entity.product.ProductCategory;
import com.suissoft.model.entity.product.Quote;

/**
 * Visitor for product {@link Entity}.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface ProductVisitor<I, R> {
	R visitOrder(Order order, I input);
	R visitProduct(Product product, I input);
	R visitProductCategory(ProductCategory productCategory, I input);
	R visitQuote(Quote quote, I input);

	interface Adapter<I, R> extends ProductVisitor<I, R> {
		default R visitOrder(Order order, I input) {return null;}
		default R visitProduct(Product product, I input) {return null;}
		default R visitProductCategory(ProductCategory productCategory, I input) {return null;}
		default R visitQuote(Quote quote, I input) {return null;}
	}
	class EntityVisitorAdapter<I, R> implements EntityVisitor.Adapter<I, R> {
		private final ProductVisitor<I, R> wrapped;
		public EntityVisitorAdapter(ProductVisitor<I, R> wrapped) {
			this.wrapped = wrapped;
		}
		public R visitOrder(Order order, I input) {return wrapped.visitOrder(order, input);}
		public R visitProduct(Product product, I input) {return wrapped.visitProduct(product, input);}
		public R visitProductCategory(ProductCategory productCategory, I input) {return wrapped.visitProductCategory(productCategory, input);}
		public R visitQuote(Quote quote, I input) {return wrapped.visitQuote(quote, input);}
	}
}
