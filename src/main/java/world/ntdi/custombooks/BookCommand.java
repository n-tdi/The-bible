package world.ntdi.custombooks;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.LinkedList;
import java.util.List;


public class BookCommand implements CommandExecutor {
    private static List<String> Godspel = new LinkedList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            translate(1);

            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(ChatColor.RED + "Genesis");
            meta.setAuthor(ChatColor.MAGIC + "God");
            meta.addPage(Godspel.get(0));

            book.setItemMeta(meta);
            p.getInventory().addItem(book);

        }
        return true;
    }

    public static void translate(int chapt)  {
        HttpResponse<String> response = Unirest.get("https://ajith-holy-bible.p.rapidapi.com/GetChapter?chapter=" + chapt + "&Book=Genesis")
                .header("X-RapidAPI-Key", "5867a41e4fmsh9e33ed99bda2765p116b97jsnd5148eac9ead")
                .header("X-RapidAPI-Host", "ajith-holy-bible.p.rapidapi.com")
                .asString();

        JSONObject jsonObject = new JSONObject(response.getBody());
        Godspel.add(jsonObject.getString("Output"));
    }

}
