/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysReceiverAddressUpdateComponent } from 'app/entities/sys-receiver-address/sys-receiver-address-update.component';
import { SysReceiverAddressService } from 'app/entities/sys-receiver-address/sys-receiver-address.service';
import { SysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

describe('Component Tests', () => {
    describe('SysReceiverAddress Management Update Component', () => {
        let comp: SysReceiverAddressUpdateComponent;
        let fixture: ComponentFixture<SysReceiverAddressUpdateComponent>;
        let service: SysReceiverAddressService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReceiverAddressUpdateComponent]
            })
                .overrideTemplate(SysReceiverAddressUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysReceiverAddressUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysReceiverAddressService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysReceiverAddress(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysReceiverAddress = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysReceiverAddress();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysReceiverAddress = entity;
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
