package savit.group2.sockstore.model.entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Vertify_Email")
public class VertifyEmail {
  @Id
  String email;
  int code;
  LocalDateTime requestAt;

  public VertifyEmail(String email) {
    this.email = email;
    this.requestAt = LocalDateTime.now();
    this.code = VertifyEmail.randomCode();
  }

  public static int randomCode() {
    Random random = new Random();
    // Generate a random 6-digit number
    int min = 122132; // Smallest 6-digit number
    int max = 998823; // Largest 6-digit number
    int randomSixDigitNumber = random.nextInt(max - min + 1) + min;
    return randomSixDigitNumber;
  }

  public boolean checkCode(int code) {
    LocalDateTime currentTime = LocalDateTime.now();
    Duration duration = Duration.between(requestAt, currentTime);
    if (duration.toMinutes() <= 10) {
      if (this.code == code) {
        return true;
      }
    }
    return false;
  }

  public boolean isAvailable() {
    LocalDateTime currentTime = LocalDateTime.now();
    Duration duration = Duration.between(requestAt, currentTime);
    if (duration.toMinutes() <= 10) {
      return true;
    }
    return false;
  }
}
