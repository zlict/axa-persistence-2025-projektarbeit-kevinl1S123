package ch.axa.punchclock.models;
 
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
 
 
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @NotBlank(message = "Cant be empty")
    @Column(name = "name",length = 5000)
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

      @OneToMany(mappedBy = "category")
  private Set<Entry> entries = new HashSet<>();

      public Set<Entry> getEntries() {
          return entries;
      }

      public void setEntries(Set<Entry> entries) {
          this.entries = entries;
      }
 

      
}
 