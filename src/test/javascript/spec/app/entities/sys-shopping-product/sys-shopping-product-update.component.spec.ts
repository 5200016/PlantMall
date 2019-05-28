/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingProductUpdateComponent } from 'app/entities/sys-shopping-product/sys-shopping-product-update.component';
import { SysShoppingProductService } from 'app/entities/sys-shopping-product/sys-shopping-product.service';
import { SysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

describe('Component Tests', () => {
    describe('SysShoppingProduct Management Update Component', () => {
        let comp: SysShoppingProductUpdateComponent;
        let fixture: ComponentFixture<SysShoppingProductUpdateComponent>;
        let service: SysShoppingProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingProductUpdateComponent]
            })
                .overrideTemplate(SysShoppingProductUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysShoppingProductUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingProductService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysShoppingProduct(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysShoppingProduct = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysShoppingProduct();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysShoppingProduct = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
