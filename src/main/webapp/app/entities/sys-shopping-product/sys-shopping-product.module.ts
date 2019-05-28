import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysShoppingProductComponent,
    SysShoppingProductDetailComponent,
    SysShoppingProductUpdateComponent,
    SysShoppingProductDeletePopupComponent,
    SysShoppingProductDeleteDialogComponent,
    sysShoppingProductRoute,
    sysShoppingProductPopupRoute
} from './';

const ENTITY_STATES = [...sysShoppingProductRoute, ...sysShoppingProductPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysShoppingProductComponent,
        SysShoppingProductDetailComponent,
        SysShoppingProductUpdateComponent,
        SysShoppingProductDeleteDialogComponent,
        SysShoppingProductDeletePopupComponent
    ],
    entryComponents: [
        SysShoppingProductComponent,
        SysShoppingProductUpdateComponent,
        SysShoppingProductDeleteDialogComponent,
        SysShoppingProductDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysShoppingProductModule {}
