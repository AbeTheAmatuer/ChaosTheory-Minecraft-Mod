package abe.chaostheory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ChaosEntityPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Identifier SUMMON_LIGHTNING_PAYLOAD_ID = Identifier.fromNamespaceAndPath(ChaosTheory.MOD_ID, "summon_lightning");

    public static final CustomPacketPayload.Type<> TYPE = new CustomPacketPayload.Type<>(SUMMON_LIGHTNING_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundSummonLightningPayload> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, ClientboundSummonLightningPayload::pos, ClientboundSummonLightningPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}