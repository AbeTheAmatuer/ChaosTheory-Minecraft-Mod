package abe.chaostheory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ChaosEntityPayload(BlockPos pos) implements CustomPacketPayload {


    public static final Identifier CHAOS_ENTITY_PAYLOAD_ID = Identifier.fromNamespaceAndPath(ChaosTheory.MOD_ID, "chaos_entity_texture");

    public static final CustomPacketPayload.Type<ChaosEntityPayload> TYPE = new CustomPacketPayload.Type<>(CHAOS_ENTITY_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, ChaosEntityPayload> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, ChaosEntityPayload::pos, ChaosEntityPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}