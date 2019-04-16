import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysProduct } from 'app/shared/model/sys-product.model';
import { SysProductService } from './sys-product.service';
import { SysProductComponent } from './sys-product.component';
import { SysProductDetailComponent } from './sys-product-detail.component';
import { SysProductUpdateComponent } from './sys-product-update.component';
import { SysProductDeletePopupComponent } from './sys-product-delete-dialog.component';
import { ISysProduct } from 'app/shared/model/sys-product.model';

@Injectable({ providedIn: 'root' })
export class SysProductResolve implements Resolve<ISysProduct> {
    constructor(private service: SysProductService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysProduct> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysProduct>) => response.ok),
                map((sysProduct: HttpResponse<SysProduct>) => sysProduct.body)
            );
        }
        return of(new SysProduct());
    }
}

export const sysProductRoute: Routes = [
    {
        path: 'sys-product',
        component: SysProductComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product/:id/view',
        component: SysProductDetailComponent,
        resolve: {
            sysProduct: SysProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product/new',
        component: SysProductUpdateComponent,
        resolve: {
            sysProduct: SysProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-product/:id/edit',
        component: SysProductUpdateComponent,
        resolve: {
            sysProduct: SysProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysProductPopupRoute: Routes = [
    {
        path: 'sys-product/:id/delete',
        component: SysProductDeletePopupComponent,
        resolve: {
            sysProduct: SysProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
