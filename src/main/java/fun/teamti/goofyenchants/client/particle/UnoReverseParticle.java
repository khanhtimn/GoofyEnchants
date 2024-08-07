package fun.teamti.goofyenchants.client.particle;

import fun.teamti.goofyenchants.init.ModParticle;
import fun.teamti.goofyenchants.init.ModSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class UnoReverseParticle extends SimpleAnimatedParticle {

    public UnoReverseParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pSprites, 1.25F);
        this.friction = 0.6F;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 0.75F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(pSprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(@NotNull SimpleParticleType pType, @NotNull ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new UnoReverseParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprites);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void handleUnoReverseAnimationClientSide(ItemStack itemStack) {
        Minecraft mc = Minecraft.getInstance();
        assert mc.player != null;
        assert mc.level != null;
        mc.particleEngine.createTrackingEmitter(mc.player, ModParticle.UNO_REVERSE_PARTICLE.get(), 30);
        mc.level.playLocalSound(mc.player.getX(), mc.player.getY(), mc.player.getZ(), ModSound.UNO_REVERSE.get(), mc.player.getSoundSource(), 1.0F, 1.0F, false);
        mc.gameRenderer.displayItemActivation(itemStack);
    }
}

