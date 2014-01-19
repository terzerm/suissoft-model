package com.suissoft.model.partner;

/**
 * Visitor for {@link Partner} children.
 *
 * @param <I> type for input passed to visit method
 * @param <R> type for result returned by visit method
 */
public interface PartnerVisitor<I, R> {
	R visitJuristicPerson(JuristicPerson p, I input);
	R visitNaturalPerson(NaturalPerson p, I input);
}
