/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderProductUpdateComponent } from 'app/entities/sys-order-product/sys-order-product-update.component';
import { SysOrderProductService } from 'app/entities/sys-order-product/sys-order-product.service';
import { SysOrderProduct } from 'app/shared/model/sys-order-product.model';

describe('Component Tests', () => {
    describe('SysOrderProduct Management Update Component', () => {
        let comp: SysOrderProductUpdateComponent;
        let fixture: ComponentFixture<SysOrderProductUpdateComponent>;
        let service: SysOrderProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderProductUpdateComponent]
            })
                .overrideTemplate(SysOrderProductUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysOrderProductUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysOrderProductService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysOrderProduct(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysOrderProduct = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysOrderProduct();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysOrderProduct = entity;
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
