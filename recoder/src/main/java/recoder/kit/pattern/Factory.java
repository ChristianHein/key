// This file is part of the RECODER library and protected by the LGPL.

package recoder.kit.pattern;

import recoder.ModelElement;
import recoder.ModelException;
import recoder.abstraction.Constructor;
import recoder.abstraction.DefaultConstructor;
import recoder.java.declaration.*;

import java.util.ArrayList;
import java.util.List;

public class Factory implements DesignPattern {

    private final List<FactoryMethod> factoryMethods;

    public Factory(List<FactoryMethod> factoryMethods) {
        this.factoryMethods = factoryMethods;
    }

    /**
     * Adds factory methods to the class for all public constructors in the given types.
     * InterfaceDeclarations are skipped as they provide no constructors.
     */
    public Factory(ClassDeclaration factoryClass, List<TypeDeclaration> products) {
        if (factoryClass == null || products == null) {
            throw new NullPointerException();
        }
        int n = products.size();
        factoryMethods = new ArrayList<FactoryMethod>(n);
        for (int i = 0; i < n; i++) {
            TypeDeclaration td = products.get(i);
            if (td instanceof ClassDeclaration) {
                addFactoryMethods((ClassDeclaration) td);
            }
        }
        List<MemberDeclaration> members = factoryClass.getMembers();
        for (int i = 0; i < factoryMethods.size(); i += 1) {
            members.add(factoryMethods.get(i).getProducer());
        }
        factoryClass.makeParentRoleValid();
    }

    /**
     * Creates factory methods for the public constructors of the given class and adds the
     * corresponding {@link MethodDeclaration}s to the class declaration.
     */
    public void addFactoryMethods(ClassDeclaration decl) {
        List<? extends Constructor> cl = decl.getConstructors();
        for (int i = 0; i < cl.size(); i++) {
            if (cl.get(i) instanceof DefaultConstructor) {
                FactoryMethod m = new FactoryMethod(decl);
                addFactoryMethod(m);
                return;
            }
            ConstructorDeclaration cons = (ConstructorDeclaration) cl.get(i);
            if (cons.isPublic()) {
                FactoryMethod m = new FactoryMethod(cons);
                addFactoryMethod(m);
            }
        }
    }

    public void addFactoryMethod(FactoryMethod m) {
        factoryMethods.add(m);
    }

    public List<FactoryMethod> getFactoryMethods() {
        return factoryMethods;
    }

    /**
     * Get total number of participants.
     *
     * @return the number of participants.
     */
    public int getParticipantCount() {
        return (factoryMethods != null) ? factoryMethods.size() : 0;
    }

    /**
     * Get a participants by its index.
     *
     * @param index an index of a participant.
     * @return the participant.
     * @throws IndexOutOfBoundsException, if the index is not in bounds.
     */
    public ModelElement getParticipantAt(int index) {
        if (factoryMethods != null) {
            return factoryMethods.get(index);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * Checks all factory methods and ensures that all methods are members of the same type.
     */
    public void validate() throws ModelException {
        if (factoryMethods == null || factoryMethods.size() == 0) {
            throw new InconsistentPatternException(
                "Factories must contain at least one factory method");
        }
        TypeDeclaration parent = null;
        for (int i = 0, s = factoryMethods.size(); i < s; i += 1) {
            FactoryMethod m = factoryMethods.get(i);
            m.validate();
            if (parent == null) {
                parent = m.getProducer().getMemberParent();
            } else if (parent != m.getProducer().getMemberParent()) {
                throw new InconsistentPatternException(
                    "Factory methods must be members of the same type");
            }
        }
    }

}
