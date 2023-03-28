package lt.viko.eif.tviliusis.computershopsystem.util;

import lt.viko.eif.tviliusis.computershopsystem.model.Account;
import lt.viko.eif.tviliusis.computershopsystem.model.Categories;
import lt.viko.eif.tviliusis.computershopsystem.model.ClientShipping;
import lt.viko.eif.tviliusis.computershopsystem.model.Components;
import lt.viko.eif.tviliusis.computershopsystem.util.HibernateUtil;
import lt.viko.eif.tviliusis.computershopsystem.util.JaxbUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The main method of the application.
 * It creates sample Account object with ClientShipping and Categories which contains Components.
 * Transactions are used to ensure the data consistency.
 * JaxbUtil is used to convert Account objects to XML format.
 *
 * @param 'args[]' the command-line arguments
 */

public class HibernateApp {
    public static void main(String[] args) {
        Components mb1 = new Components("Asus ROG STRIX B550", 120.99);
        Components mb2 = new Components("Gigabyte Elite AX V2", 169.99);
        Categories category = new Categories("Motherboard", List.of(mb1, mb2));
        ClientShipping clientShipping = new ClientShipping(869784526, "Verkiu g.45", "LT00762");
        Account account1 = new Account("Tadas", "1515", clientShipping, List.of(category));

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(account1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Account> accounts = session.createQuery("from Account", Account.class).list();
            accounts.forEach(acc -> System.out.println(acc));
            System.out.println("__");
            accounts.forEach(acc -> JaxbUtil.convertToXML(acc));

            System.in.read();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //server.shutdown();
        }
    }

}


