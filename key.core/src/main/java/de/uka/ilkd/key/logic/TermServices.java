package de.uka.ilkd.key.logic;

/**
 * This interface defines the basic functionalities of services required to construct {@link Term}s.
 *
 * @author Richard Bubel
 */
public interface TermServices {

    /**
     * returns the namespaces for functions, predicates etc.
     *
     * @return the proof specific namespaces
     */
    public abstract NamespaceSet getNamespaces();

    /**
     * Returns the {@link TermBuilder} used to create {@link Term}s.
     *
     * @return The {@link TermBuilder} used to create {@link Term}s.
     */
    public abstract TermBuilder getTermBuilder();

    /**
     *
     * Returns either the cache backed or raw {@link TermBuilder} used to create {@link Term}s.
     * Usually the cache backed version is the intended one. The non-cached version is for use cases
     * where a lot of intermediate terms are created of which most exist only for a very short time.
     * To avoid polluting the cache it is then recommended to use the non-cache version
     *
     * @return The {@link TermBuilder} used to create {@link Term}s.
     */
    public abstract TermBuilder getTermBuilder(boolean withCache);

    /**
     * Returns the {@link TermBuilder} used to create {@link Term}s.
     *
     * @return The {@link TermBuilder} used to create {@link Term}s.
     */
    public abstract TermFactory getTermFactory();

}
