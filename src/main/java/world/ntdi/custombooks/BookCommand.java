package world.ntdi.custombooks;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;


public class BookCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(ChatColor.RED + "Genesis");
            meta.setAuthor(ChatColor.MAGIC + "God");
            meta.addPage("In the beginning God created the heavens and the earth.",
                    "Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.",
                    "And God said, “Let there be light,” and there was light.",
                    "God saw that the light was good, and he separated the light from the darkness.",
                    "God called the light “day,” and the darkness he called “night.” And there was evening, and there was morning—the first day."
            );

            book.setItemMeta(meta);
            p.getInventory().addItem(book);

            translate();


        }
        return true;
    }

    public void translate(int start)  {
        if (start > 29) return;
        HttpResponse<String> response = Unirest.get("https://ajith-holy-bible.p.rapidapi.com/GetVerseOfaChapter?Verse=2&chapter=1&Book=Genesis")
                .header("X-RapidAPI-Key", "5867a41e4fmsh9e33ed99bda2765p116b97jsnd5148eac9ead")
                .header("X-RapidAPI-Host", "ajith-holy-bible.p.rapidapi.com")
                .asString();

        System.out.println(response.getBody());
    }

}
